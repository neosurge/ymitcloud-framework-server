package org.springframework.messaging.handler.invocation;

import com.ymit.framework.tenant.core.context.TenantContextHolder;
import com.ymit.framework.tenant.core.util.TenantUtils;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.HandlerMethod;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;

import static com.ymit.framework.web.core.util.WebFrameworkUtils.HEADER_TENANT_ID;

/**
 * Extension of {@link HandlerMethod} that invokes the underlying method with
 * argument values resolved from the current HTTP request through a list of
 * {@link HandlerMethodArgumentResolver}.
 * <p>
 * 针对 rabbitmq-spring 和 kafka-spring，不存在合适的拓展点，可以实现 Consumer 消费前，读取 Header 中的 tenant-id 设置到 {@link TenantContextHolder} 中
 * TODO ：持续跟进，看看有没新的拓展点
 *
 * @author Rossen Stoyanchev
 * @author Juergen Hoeller
 * @since 4.0
 */
public class InvocableHandlerMethod extends HandlerMethod {
    private static final Object[] EMPTY_ARGS = new Object[0];
    private HandlerMethodArgumentResolverComposite resolvers = new HandlerMethodArgumentResolverComposite();
    private ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    /**
     * Create an instance from a {@code HandlerMethod}.
     */
    public InvocableHandlerMethod(HandlerMethod handlerMethod) {
        super(handlerMethod);
    }

    /**
     * Create an instance from a bean instance and a method.
     */
    public InvocableHandlerMethod(Object bean, Method method) {
        super(bean, method);
    }

    /**
     * Construct a new handler method with the given bean instance, method name and parameters.
     *
     * @param bean           the object bean
     * @param methodName     the method name
     * @param parameterTypes the method parameter types
     * @throws NoSuchMethodException when the method cannot be found
     */
    public InvocableHandlerMethod(Object bean, String methodName, Class<?>... parameterTypes)
            throws NoSuchMethodException {
        super(bean, methodName, parameterTypes);
    }

    /**
     * Set {@link HandlerMethodArgumentResolver HandlerMethodArgumentResolvers} to use for resolving method argument values.
     */
    public void setMessageMethodArgumentResolvers(HandlerMethodArgumentResolverComposite argumentResolvers) {
        this.resolvers = argumentResolvers;
    }

    /**
     * Set the ParameterNameDiscoverer for resolving parameter names when needed
     * (e.g. default request attribute name).
     * <p>Default is a {@link org.springframework.core.DefaultParameterNameDiscoverer}.
     */
    public void setParameterNameDiscoverer(ParameterNameDiscoverer parameterNameDiscoverer) {
        this.parameterNameDiscoverer = parameterNameDiscoverer;
    }

    /**
     * Invoke the method after resolving its argument values in the context of the given message.
     * <p>Argument values are commonly resolved through
     * {@link HandlerMethodArgumentResolver HandlerMethodArgumentResolvers}.
     * The {@code providedArgs} parameter however may supply argument values to be used directly,
     * i.e. without argument resolution.
     * <p>Delegates to {@link #getMethodArgumentValues} and calls {@link #doInvoke} with the
     * resolved arguments.
     *
     * @param message      the current message being processed
     * @param providedArgs "given" arguments matched by type, not resolved
     * @return the raw value returned by the invoked method
     * @throws Exception raised if no suitable argument resolver can be found,
     *                   or if the method raised an exception
     * @see #getMethodArgumentValues
     * @see #doInvoke
     */
    @Nullable
    public Object invoke(Message<?> message, Object... providedArgs) throws Exception {
        Object[] args = this.getMethodArgumentValues(message, providedArgs);
        if (this.logger.isTraceEnabled()) {
            this.logger.trace("Arguments: " + Arrays.toString(args));
        }
        // 注意：如下是本类的改动点！！！
        // 情况一：无租户编号的情况
        Long tenantId = this.parseTenantId(message);
        if (tenantId == null) {
            return this.doInvoke(args);
        }
        // 情况二：有租户的情况下
        return TenantUtils.execute(tenantId, () -> this.doInvoke(args));
    }

    @Nullable
    private Long parseTenantId(Message<?> message) {
        Object tenantId = message.getHeaders().get(HEADER_TENANT_ID);
        if (tenantId == null) {
            return null;
        }
        if (tenantId instanceof Long) {
            return (Long) tenantId;
        }
        if (tenantId instanceof Number) {
            return ((Number) tenantId).longValue();
        }
        if (tenantId instanceof String) {
            return Long.parseLong((String) tenantId);
        }
        if (tenantId instanceof byte[]) {
            return Long.parseLong(new String((byte[]) tenantId));
        }
        throw new IllegalArgumentException("未知的数据类型：" + tenantId);
    }

    /**
     * Get the method argument values for the current message, checking the provided
     * argument values and falling back to the configured argument resolvers.
     * <p>The resulting array will be passed into {@link #doInvoke}.
     *
     * @since 5.1.2
     */
    protected Object[] getMethodArgumentValues(Message<?> message, Object... providedArgs) throws Exception {
        MethodParameter[] parameters = this.getMethodParameters();
        if (ObjectUtils.isEmpty(parameters)) {
            return EMPTY_ARGS;
        }
        Object[] args = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            MethodParameter parameter = parameters[i];
            parameter.initParameterNameDiscovery(this.parameterNameDiscoverer);
            args[i] = findProvidedArgument(parameter, providedArgs);
            if (args[i] != null) {
                continue;
            }
            if (!this.resolvers.supportsParameter(parameter)) {
                throw new MethodArgumentResolutionException(
                        message, parameter, formatArgumentError(parameter, "No suitable resolver"));
            }
            try {
                args[i] = this.resolvers.resolveArgument(parameter, message);
            } catch (Exception ex) {
                // Leave stack trace for later, exception may actually be resolved and handled...
                if (this.logger.isDebugEnabled()) {
                    String exMsg = ex.getMessage();
                    if (exMsg != null && !exMsg.contains(parameter.getExecutable().toGenericString())) {
                        this.logger.debug(formatArgumentError(parameter, exMsg));
                    }
                }
                throw ex;
            }
        }
        return args;
    }

    /**
     * Invoke the handler method with the given argument values.
     */
    @Nullable
    protected Object doInvoke(Object... args) throws Exception {
        try {
            return this.getBridgedMethod().invoke(this.getBean(), args);
        } catch (IllegalArgumentException ex) {
            this.assertTargetBean(this.getBridgedMethod(), this.getBean(), args);
            String text = (ex.getMessage() == null || ex.getCause() instanceof NullPointerException) ?
                    "Illegal argument" : ex.getMessage();
            throw new IllegalStateException(this.formatInvokeError(text, args), ex);
        } catch (InvocationTargetException ex) {
            // Unwrap for HandlerExceptionResolvers ...
            Throwable targetException = ex.getTargetException();
            if (targetException instanceof RuntimeException) {
                RuntimeException runtimeException = (RuntimeException) targetException;
                throw runtimeException;
            } else if (targetException instanceof Error) {
                Error error = (Error) targetException;
                throw error;
            } else if (targetException instanceof Exception) {
                Exception exception = (Exception) targetException;
                throw exception;
            } else {
                throw new IllegalStateException(this.formatInvokeError("Invocation failure", args), targetException);
            }
        }
    }

    MethodParameter getAsyncReturnValueType(@Nullable Object returnValue) {
        return new AsyncResultMethodParameter(returnValue);
    }

    private class AsyncResultMethodParameter extends AnnotatedMethodParameter {
        @Nullable
        private final Object returnValue;
        private final ResolvableType returnType;

        public AsyncResultMethodParameter(@Nullable Object returnValue) {
            super(-1);
            this.returnValue = returnValue;
            this.returnType = ResolvableType.forType(super.getGenericParameterType()).getGeneric();
        }

        protected AsyncResultMethodParameter(AsyncResultMethodParameter original) {
            super(original);
            this.returnValue = original.returnValue;
            this.returnType = original.returnType;
        }

        @Override
        public Class<?> getParameterType() {
            if (this.returnValue != null) {
                return this.returnValue.getClass();
            }
            if (!ResolvableType.NONE.equals(this.returnType)) {
                return this.returnType.toClass();
            }
            return super.getParameterType();
        }

        @Override
        public Type getGenericParameterType() {
            return this.returnType.getType();
        }

        @Override
        public AsyncResultMethodParameter clone() {
            return new AsyncResultMethodParameter(this);
        }
    }
}
