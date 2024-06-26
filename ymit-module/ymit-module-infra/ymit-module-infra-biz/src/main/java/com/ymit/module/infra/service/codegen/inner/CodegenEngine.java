package com.ymit.module.infra.service.codegen.inner;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.engine.velocity.VelocityEngine;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.ymit.framework.common.data.CommonResult;
import com.ymit.framework.common.data.PageParam;
import com.ymit.framework.common.data.PageResult;
import com.ymit.framework.common.exception.util.ServiceExceptionUtil;
import com.ymit.framework.common.util.collection.CollectionUtils;
import com.ymit.framework.common.util.date.DateUtils;
import com.ymit.framework.common.util.date.LocalDateTimeUtils;
import com.ymit.framework.common.util.object.BeanUtils;
import com.ymit.framework.common.util.object.ObjectUtils;
import com.ymit.framework.common.util.string.StrUtils;
import com.ymit.framework.excel.core.annotations.DictFormat;
import com.ymit.framework.excel.core.convert.DictConvert;
import com.ymit.framework.excel.core.util.ExcelUtils;
import com.ymit.framework.mybatis.core.dataobject.BaseDO;
import com.ymit.framework.mybatis.core.mapper.BaseMapperX;
import com.ymit.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ymit.framework.operatelog.core.annotations.OperateLog;
import com.ymit.framework.operatelog.core.enums.OperateTypeEnum;
import com.ymit.module.infra.dal.dataobject.codegen.CodegenColumnDO;
import com.ymit.module.infra.dal.dataobject.codegen.CodegenTableDO;
import com.ymit.module.infra.enums.codegen.CodegenFrontTypeEnum;
import com.ymit.module.infra.enums.codegen.CodegenSceneEnum;
import com.ymit.module.infra.enums.codegen.CodegenTemplateTypeEnum;
import com.ymit.module.infra.framework.codegen.config.CodegenProperties;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.*;

import static cn.hutool.core.map.MapUtil.getStr;
import static cn.hutool.core.text.CharSequenceUtil.*;

/**
 * 代码生成的引擎，用于具体生成代码
 * 目前基于 {@link org.apache.velocity.app.Velocity} 模板引擎实现
 * <p>
 * 考虑到 Java 模板引擎的框架非常多，Freemarker、Velocity、Thymeleaf 等等，所以我们采用 hutool 封装的 {@link cn.hutool.extra.template.Template} 抽象
 *
 * @author 云码源码
 */
@Component
public class CodegenEngine {

    /**
     * 后端的模板配置
     * <p>
     * key：模板在 resources 的地址
     * value：生成的路径
     */
    private static final Map<String, String> SERVER_TEMPLATES = MapUtil.<String, String>builder(new LinkedHashMap<>()) // 有序
            // Java module-biz Main
//            .put(javaTemplatePath("controller/vo/pageReqVO"), javaModuleImplVOFilePath("PageReqVO"))
            .put(javaTemplatePath("controller/vo/queryReqVO"), javaModuleImplVOFilePath("QueryReqVO"))
//            .put(javaTemplatePath("controller/vo/listReqVO"), javaModuleImplVOFilePath("ListReqVO"))
            .put(javaTemplatePath("controller/vo/respVO"), javaModuleImplVOFilePath("RespVO"))
            .put(javaTemplatePath("controller/vo/saveBaseVO"), javaModuleImplVOFilePath("SaveBaseVO"))
//            .put(javaTemplatePath("controller/vo/createReqVO"), javaModuleImplVOFilePath("CreateReqVO"))
//            .put(javaTemplatePath("controller/vo/updateReqVO"), javaModuleImplVOFilePath("UpdateReqVO"))
            .put(javaTemplatePath("controller/controller"), javaModuleImplControllerFilePath())
            .put(javaTemplatePath("dal/do"),
                    javaModuleImplMainFilePath("dal/dataobject/${table.businessName}/${table.className}DO"))
            .put(javaTemplatePath("dal/do_sub"), // 特殊：主子表专属逻辑
                    javaModuleImplMainFilePath("dal/dataobject/${table.businessName}/${subTable.className}DO"))
            .put(javaTemplatePath("dal/mapper"),
                    javaModuleImplMainFilePath("dal/mysql/${table.businessName}/${table.className}Mapper"))
            .put(javaTemplatePath("dal/mapper_sub"), // 特殊：主子表专属逻辑
                    javaModuleImplMainFilePath("dal/mysql/${table.businessName}/${subTable.className}Mapper"))
            .put(javaTemplatePath("dal/mapper.xml"), mapperXmlFilePath())
            .put(javaTemplatePath("service/serviceImpl"),
                    javaModuleImplMainFilePath("service/${table.businessName}/${table.className}ServiceImpl"))
            .put(javaTemplatePath("service/service"),
                    javaModuleImplMainFilePath("service/${table.businessName}/${table.className}Service"))
            // Java module-biz Test
            .put(javaTemplatePath("test/serviceTest"),
                    javaModuleImplTestFilePath("service/${table.businessName}/${table.className}ServiceImplTest"))
            // Java module-api Main
            .put(javaTemplatePath("enums/errorcode"), javaModuleApiMainFilePath("ErrorCodeConstants_手动操作"))
            // SQL
            .put("codegen/sql/sql.vm", "sql/sql.sql")
            .put("codegen/sql/h2.vm", "sql/h2.sql")
            .build();

    /**
     * 后端的配置模版
     * <p>
     * key1：UI 模版的类型 {@link CodegenFrontTypeEnum#getType()}
     * key2：模板在 resources 的地址
     * value：生成的路径
     */
    private static final Table<Integer, String, String> FRONT_TEMPLATES = ImmutableTable.<Integer, String, String>builder()
            // Vue2 标准模版
            .put(CodegenFrontTypeEnum.VUE2.getType(), vueTemplatePath("views/index.vue"),
                    vueFilePath("views/${table.moduleName}/${table.businessName}/index.vue"))
            .put(CodegenFrontTypeEnum.VUE2.getType(), vueTemplatePath("api/api.js"),
                    vueFilePath("api/${table.moduleName}/${table.businessName}/index.js"))
            .put(CodegenFrontTypeEnum.VUE2.getType(), vueTemplatePath("views/form.vue"),
                    vueFilePath("views/${table.moduleName}/${table.businessName}/${simpleClassName}Form.vue"))
            .put(CodegenFrontTypeEnum.VUE2.getType(), vueTemplatePath("views/components/form_sub_normal.vue"),  // 特殊：主子表专属逻辑
                    vueFilePath("views/${table.moduleName}/${table.businessName}/components/${subSimpleClassName}Form.vue"))
            .put(CodegenFrontTypeEnum.VUE2.getType(), vueTemplatePath("views/components/form_sub_inner.vue"),  // 特殊：主子表专属逻辑
                    vueFilePath("views/${table.moduleName}/${table.businessName}/components/${subSimpleClassName}Form.vue"))
            .put(CodegenFrontTypeEnum.VUE2.getType(), vueTemplatePath("views/components/form_sub_erp.vue"),  // 特殊：主子表专属逻辑
                    vueFilePath("views/${table.moduleName}/${table.businessName}/components/${subSimpleClassName}Form.vue"))
            .put(CodegenFrontTypeEnum.VUE2.getType(), vueTemplatePath("views/components/list_sub_inner.vue"),  // 特殊：主子表专属逻辑
                    vueFilePath("views/${table.moduleName}/${table.businessName}/components/${subSimpleClassName}List.vue"))
            .put(CodegenFrontTypeEnum.VUE2.getType(), vueTemplatePath("views/components/list_sub_erp.vue"),  // 特殊：主子表专属逻辑
                    vueFilePath("views/${table.moduleName}/${table.businessName}/components/${subSimpleClassName}List.vue"))
            // Vue3 标准模版
            .put(CodegenFrontTypeEnum.VUE3.getType(), vue3TemplatePath("views/index.vue"),
                    vue3FilePath("views/${table.moduleName}/${table.businessName}/index.vue"))
            .put(CodegenFrontTypeEnum.VUE3.getType(), vue3TemplatePath("views/form.vue"),
                    vue3FilePath("views/${table.moduleName}/${table.businessName}/${simpleClassName}Form.vue"))
            .put(CodegenFrontTypeEnum.VUE3.getType(), vue3TemplatePath("views/components/form_sub_normal.vue"),  // 特殊：主子表专属逻辑
                    vue3FilePath("views/${table.moduleName}/${table.businessName}/components/${subSimpleClassName}Form.vue"))
            .put(CodegenFrontTypeEnum.VUE3.getType(), vue3TemplatePath("views/components/form_sub_inner.vue"),  // 特殊：主子表专属逻辑
                    vue3FilePath("views/${table.moduleName}/${table.businessName}/components/${subSimpleClassName}Form.vue"))
            .put(CodegenFrontTypeEnum.VUE3.getType(), vue3TemplatePath("views/components/form_sub_erp.vue"),  // 特殊：主子表专属逻辑
                    vue3FilePath("views/${table.moduleName}/${table.businessName}/components/${subSimpleClassName}Form.vue"))
            .put(CodegenFrontTypeEnum.VUE3.getType(), vue3TemplatePath("views/components/list_sub_inner.vue"),  // 特殊：主子表专属逻辑
                    vue3FilePath("views/${table.moduleName}/${table.businessName}/components/${subSimpleClassName}List.vue"))
            .put(CodegenFrontTypeEnum.VUE3.getType(), vue3TemplatePath("views/components/list_sub_erp.vue"),  // 特殊：主子表专属逻辑
                    vue3FilePath("views/${table.moduleName}/${table.businessName}/components/${subSimpleClassName}List.vue"))
            .put(CodegenFrontTypeEnum.VUE3.getType(), vue3TemplatePath("api/api.ts"),
                    vue3FilePath("api/${table.moduleName}/${table.businessName}/index.ts"))
            // Vue3 Schema 模版
            .put(CodegenFrontTypeEnum.VUE3_SCHEMA.getType(), vue3SchemaTemplatePath("views/data.ts"),
                    vue3FilePath("views/${table.moduleName}/${table.businessName}/${classNameVar}.data.ts"))
            .put(CodegenFrontTypeEnum.VUE3_SCHEMA.getType(), vue3SchemaTemplatePath("views/index.vue"),
                    vue3FilePath("views/${table.moduleName}/${table.businessName}/index.vue"))
            .put(CodegenFrontTypeEnum.VUE3_SCHEMA.getType(), vue3SchemaTemplatePath("views/form.vue"),
                    vue3FilePath("views/${table.moduleName}/${table.businessName}/${simpleClassName}Form.vue"))
            .put(CodegenFrontTypeEnum.VUE3_SCHEMA.getType(), vue3SchemaTemplatePath("api/api.ts"),
                    vue3FilePath("api/${table.moduleName}/${table.businessName}/index.ts"))
            // Vue3 vben 模版
            .put(CodegenFrontTypeEnum.VUE3_VBEN.getType(), vue3VbenTemplatePath("views/data.ts"),
                    vue3FilePath("views/${table.moduleName}/${table.businessName}/${classNameVar}.data.ts"))
            .put(CodegenFrontTypeEnum.VUE3_VBEN.getType(), vue3VbenTemplatePath("views/index.vue"),
                    vue3FilePath("views/${table.moduleName}/${table.businessName}/index.vue"))
            .put(CodegenFrontTypeEnum.VUE3_VBEN.getType(), vue3VbenTemplatePath("views/form.vue"),
                    vue3FilePath("views/${table.moduleName}/${table.businessName}/${simpleClassName}Modal.vue"))
            .put(CodegenFrontTypeEnum.VUE3_VBEN.getType(), vue3VbenTemplatePath("api/api.ts"),
                    vue3FilePath("api/${table.moduleName}/${table.businessName}/index.ts"))
            .build();
    /**
     * 模板引擎，由 hutool 实现
     */
    private final TemplateEngine templateEngine;
    /**
     * 全局通用变量映射
     */
    private final Map<String, Object> globalBindingMap = new HashMap<>();
    @Resource
    private CodegenProperties codegenProperties;

    public CodegenEngine() {
        // 初始化 TemplateEngine 属性
        TemplateConfig config = new TemplateConfig();
        config.setResourceMode(TemplateConfig.ResourceMode.CLASSPATH);
        this.templateEngine = new VelocityEngine(config);
    }

    private static String javaTemplatePath(String path) {
        return "codegen/java/" + path + ".vm";
    }

    private static String javaModuleImplVOFilePath(String path) {
        return javaModuleFilePath("controller/${sceneEnum.basePackage}/${table.businessName}/" +
                "vo/${sceneEnum.prefixClass}${table.className}" + path, "biz", "main");
    }

    private static String javaModuleImplControllerFilePath() {
        return javaModuleFilePath("controller/${sceneEnum.basePackage}/${table.businessName}/" +
                "${sceneEnum.prefixClass}${table.className}Controller", "biz", "main");
    }

    private static String javaModuleImplMainFilePath(String path) {
        return javaModuleFilePath(path, "biz", "main");
    }

    private static String javaModuleApiMainFilePath(String path) {
        return "ymit-module-${table.moduleName}/" + // 顶级模块
                "ymit-module-${table.moduleName}-api/" + path + ".java";
//        return javaModuleFilePath(path, "api", "main");
    }

    private static String javaModuleImplTestFilePath(String path) {
        return javaModuleFilePath(path, "biz", "test");
    }

    private static String javaModuleFilePath(String path, String module, String src) {
        return "ymit-module-${table.moduleName}/" + // 顶级模块
                "ymit-module-${table.moduleName}-" + module + "/" + // 子模块
                "src/" + src + "/java/${basePackage}/module/${table.moduleName}/" + path + ".java";
    }

    private static String mapperXmlFilePath() {
        return "ymit-module-${table.moduleName}/" + // 顶级模块
                "ymit-module-${table.moduleName}-biz/" + // 子模块
                "src/main/resources/mapper/${table.businessName}/${table.className}Mapper.xml";
    }

    private static String vueTemplatePath(String path) {
        return "codegen/vue/" + path + ".vm";
    }

    private static String vueFilePath(String path) {
        return "ymit-ui-${sceneEnum.basePackage}-vue2/" + // 顶级目录
                "src/" + path;
    }

    private static String vue3TemplatePath(String path) {
        return "codegen/vue3/" + path + ".vm";
    }

    private static String vue3FilePath(String path) {
        return "ymit-ui-${sceneEnum.basePackage}-vue3/" + // 顶级目录
                "src/" + path;
    }

    private static String vue3SchemaTemplatePath(String path) {
        return "codegen/vue3_schema/" + path + ".vm";
    }

    private static String vue3VbenTemplatePath(String path) {
        return "codegen/vue3_vben/" + path + ".vm";
    }

    private static boolean isSubTemplate(String path) {
        return path.contains("_sub");
    }

    private static boolean isPageReqVOTemplate(String path) {
        return path.contains("pageReqVO");
    }

    private static boolean isListReqVOTemplate(String path) {
        return path.contains("listReqVO");
    }

    @PostConstruct
    @VisibleForTesting
    void initGlobalBindingMap() {
        // 全局配置
        this.globalBindingMap.put("basePackage", this.codegenProperties.getBasePackage());
        this.globalBindingMap.put("baseFrameworkPackage", this.codegenProperties.getBasePackage()
                + '.' + "framework"); // 用于后续获取测试类的 package 地址
        // 全局 Java Bean
        this.globalBindingMap.put("CommonResultClassName", CommonResult.class.getName());
        this.globalBindingMap.put("PageResultClassName", PageResult.class.getName());
        // VO 类，独有字段
        this.globalBindingMap.put("PageParamClassName", PageParam.class.getName());
        this.globalBindingMap.put("DictFormatClassName", DictFormat.class.getName());
        // DO 类，独有字段
        this.globalBindingMap.put("BaseDOClassName", BaseDO.class.getName());
        this.globalBindingMap.put("baseDOFields", CodegenBuilder.BASE_DO_FIELDS);
        this.globalBindingMap.put("QueryWrapperClassName", LambdaQueryWrapperX.class.getName());
        this.globalBindingMap.put("BaseMapperClassName", BaseMapperX.class.getName());
        // Util 工具类
        this.globalBindingMap.put("ServiceExceptionUtilClassName", ServiceExceptionUtil.class.getName());
        this.globalBindingMap.put("DateUtilsClassName", DateUtils.class.getName());
        this.globalBindingMap.put("ExcelUtilsClassName", ExcelUtils.class.getName());
        this.globalBindingMap.put("LocalDateTimeUtilsClassName", LocalDateTimeUtils.class.getName());
        this.globalBindingMap.put("ObjectUtilsClassName", ObjectUtils.class.getName());
        this.globalBindingMap.put("DictConvertClassName", DictConvert.class.getName());
        this.globalBindingMap.put("OperateLogClassName", OperateLog.class.getName());
        this.globalBindingMap.put("OperateTypeEnumClassName", OperateTypeEnum.class.getName());
        this.globalBindingMap.put("BeanUtils", BeanUtils.class.getName());
    }

    /**
     * 生成代码
     *
     * @param table          表定义
     * @param columns        table 的字段定义数组
     * @param subTables      子表数组，当且仅当主子表时使用
     * @param subColumnsList subTables 的字段定义数组
     * @return 生成的代码，key 是路径，value 是对应代码
     */
    public Map<String, String> execute(CodegenTableDO table, List<CodegenColumnDO> columns,
                                       List<CodegenTableDO> subTables, List<List<CodegenColumnDO>> subColumnsList) {
        // 1.1 初始化 bindMap 上下文
        Map<String, Object> bindingMap = this.initBindingMap(table, columns, subTables, subColumnsList);
        // 1.2 获得模版
        Map<String, String> templates = this.getTemplates(table.getFrontType());

        // 2. 执行生成
        Map<String, String> result = Maps.newLinkedHashMapWithExpectedSize(templates.size()); // 有序
        templates.forEach((vmPath, filePath) -> {
            // 2.1 特殊：主子表专属逻辑
            if (isSubTemplate(vmPath)) {
                this.generateSubCode(table, subTables, result, vmPath, filePath, bindingMap);
                return;
                // 2.2 特殊：树表专属逻辑
            } else if (isPageReqVOTemplate(vmPath)) {
                // 减少多余的类生成，例如说 PageVO.java 类
                if (CodegenTemplateTypeEnum.isTree(table.getTemplateType())) {
                    return;
                }
            } else if (isListReqVOTemplate(vmPath)) {
                // 减少多余的类生成，例如说 ListVO.java 类
                if (!CodegenTemplateTypeEnum.isTree(table.getTemplateType())) {
                    return;
                }
            }
            // 2.3 默认生成
            this.generateCode(result, vmPath, filePath, bindingMap);
        });
        return result;
    }

    private void generateCode(Map<String, String> result, String vmPath,
                              String filePath, Map<String, Object> bindingMap) {
        filePath = this.formatFilePath(filePath, bindingMap);
        String content = this.templateEngine.getTemplate(vmPath).render(bindingMap);
        // 格式化代码
        content = this.prettyCode(content);
        result.put(filePath, content);
    }

    private void generateSubCode(CodegenTableDO table, List<CodegenTableDO> subTables,
                                 Map<String, String> result, String vmPath,
                                 String filePath, Map<String, Object> bindingMap) {
        // 没有子表，所以不生成
        if (CollUtil.isEmpty(subTables)) {
            return;
        }
        // 主子表的模式匹配。目的：过滤掉个性化的模版
        if (vmPath.contains("_normal")
                && ObjectUtil.notEqual(table.getTemplateType(), CodegenTemplateTypeEnum.MASTER_NORMAL.getType())) {
            return;
        }
        if (vmPath.contains("_erp")
                && ObjectUtil.notEqual(table.getTemplateType(), CodegenTemplateTypeEnum.MASTER_ERP.getType())) {
            return;
        }
        if (vmPath.contains("_inner")
                && ObjectUtil.notEqual(table.getTemplateType(), CodegenTemplateTypeEnum.MASTER_INNER.getType())) {
            return;
        }

        // 逐个生成
        for (int i = 0; i < subTables.size(); i++) {
            bindingMap.put("subIndex", i);
            this.generateCode(result, vmPath, filePath, bindingMap);
        }
        bindingMap.remove("subIndex");
    }

    /**
     * 格式化生成后的代码
     * <p>
     * 因为尽量让 vm 模版简单，所以统一的处理都在这个方法。
     * 如果不处理，Vue 的 Pretty 格式校验可能会报错
     *
     * @param content 格式化前的代码
     * @return 格式化后的代码
     */
    private String prettyCode(String content) {
        // Vue 界面：去除字段后面多余的 , 逗号，解决前端的 Pretty 代码格式检查的报错
        content = content.replaceAll(",\n}", "\n}").replaceAll(",\n  }", "\n  }");
        // Vue 界面：去除多的 dateFormatter，只有一个的情况下，说明没使用到
        if (StrUtil.count(content, "dateFormatter") == 1) {
            content = StrUtils.removeLineContains(content, "dateFormatter");
        }
        // Vue2 界面：修正 $refs
        if (StrUtil.count(content, "this.refs") >= 1) {
            content = content.replace("this.refs", "this.$refs");
        }
        // Vue 界面：去除多的 dict 相关，只有一个的情况下，说明没使用到
        if (StrUtil.count(content, "getIntDictOptions") == 1) {
            content = content.replace("getIntDictOptions, ", "");
        }
        if (StrUtil.count(content, "getStrDictOptions") == 1) {
            content = content.replace("getStrDictOptions, ", "");
        }
        if (StrUtil.count(content, "getBoolDictOptions") == 1) {
            content = content.replace("getBoolDictOptions, ", "");
        }
        if (StrUtil.count(content, "DICT_TYPE.") == 0) {
            content = StrUtils.removeLineContains(content, "DICT_TYPE");
        }
        return content;
    }

    private Map<String, Object> initBindingMap(CodegenTableDO table, List<CodegenColumnDO> columns,
                                               List<CodegenTableDO> subTables, List<List<CodegenColumnDO>> subColumnsList) {
        // 创建 bindingMap
        Map<String, Object> bindingMap = new HashMap<>(this.globalBindingMap);
        bindingMap.put("table", table);
        bindingMap.put("columns", columns);
        bindingMap.put("primaryColumn", CollectionUtils.findFirst(columns, CodegenColumnDO::getPrimaryKey)); // 主键字段
        bindingMap.put("sceneEnum", CodegenSceneEnum.valueOf(table.getScene()));

        // className 相关
        // 去掉指定前缀，将 TestDictType 转换成 DictType. 因为在 create 等方法后，不需要带上 Test 前缀
        String simpleClassName = removePrefix(table.getClassName(), upperFirst(table.getModuleName()));
        bindingMap.put("simpleClassName", simpleClassName);
        bindingMap.put("simpleClassName_underlineCase", toUnderlineCase(simpleClassName)); // 将 DictType 转换成 dict_type
        bindingMap.put("classNameVar", lowerFirst(simpleClassName)); // 将 DictType 转换成 dictType，用于变量
        // 将 DictType 转换成 dict-type
        //String simpleClassNameStrikeCase = toSymbolCase(simpleClassName, '-');
        String simpleClassNameStrikeCase = table.getBusinessName().replaceAll("/", "-");
        bindingMap.put("simpleClassName_strikeCase", simpleClassNameStrikeCase);
        // permission 前缀
        bindingMap.put("permissionPrefix", table.getModuleName() + ":" + simpleClassNameStrikeCase);

        // 特殊：树表专属逻辑
        if (CodegenTemplateTypeEnum.isTree(table.getTemplateType())) {
            CodegenColumnDO treeParentColumn = CollUtil.findOne(columns,
                    column -> Objects.equals(column.getId(), table.getTreeParentColumnId()));
            bindingMap.put("treeParentColumn", treeParentColumn);
            bindingMap.put("treeParentColumn_javaField_underlineCase", toUnderlineCase(treeParentColumn.getJavaField()));
            CodegenColumnDO treeNameColumn = CollUtil.findOne(columns,
                    column -> Objects.equals(column.getId(), table.getTreeNameColumnId()));
            bindingMap.put("treeNameColumn", treeNameColumn);
            bindingMap.put("treeNameColumn_javaField_underlineCase", toUnderlineCase(treeNameColumn.getJavaField()));
        }

        // 特殊：主子表专属逻辑
        if (CollUtil.isNotEmpty(subTables)) {
            // 创建 bindingMap
            bindingMap.put("subTables", subTables);
            bindingMap.put("subColumnsList", subColumnsList);
            List<CodegenColumnDO> subPrimaryColumns = new ArrayList<>();
            List<CodegenColumnDO> subJoinColumns = new ArrayList<>();
            List<String> subJoinColumnStrikeCases = new ArrayList<>();
            List<String> subSimpleClassNames = new ArrayList<>();
            List<String> subClassNameVars = new ArrayList<>();
            List<String> simpleClassNameUnderlineCases = new ArrayList<>();
            List<String> subSimpleClassNameStrikeCases = new ArrayList<>();
            for (int i = 0; i < subTables.size(); i++) {
                CodegenTableDO subTable = subTables.get(i);
                List<CodegenColumnDO> subColumns = subColumnsList.get(i);
                subPrimaryColumns.add(CollectionUtils.findFirst(subColumns, CodegenColumnDO::getPrimaryKey)); //
                CodegenColumnDO subColumn = CollectionUtils.findFirst(subColumns, // 关联的字段
                        column -> Objects.equals(column.getId(), subTable.getSubJoinColumnId()));
                subJoinColumns.add(subColumn);
                subJoinColumnStrikeCases.add(toSymbolCase(subColumn.getJavaField(), '-')); // 将 DictType 转换成 dict-type
                // className 相关
                String subSimpleClassName = removePrefix(subTable.getClassName(), upperFirst(subTable.getModuleName()));
                subSimpleClassNames.add(subSimpleClassName);
                simpleClassNameUnderlineCases.add(toUnderlineCase(subSimpleClassName)); // 将 DictType 转换成 dict_type
                subClassNameVars.add(lowerFirst(subSimpleClassName)); // 将 DictType 转换成 dictType，用于变量
                subSimpleClassNameStrikeCases.add(toSymbolCase(subSimpleClassName, '-')); // 将 DictType 转换成 dict-type
            }
            bindingMap.put("subPrimaryColumns", subPrimaryColumns);
            bindingMap.put("subJoinColumns", subJoinColumns);
            bindingMap.put("subJoinColumn_strikeCases", subJoinColumnStrikeCases);
            bindingMap.put("subSimpleClassNames", subSimpleClassNames);
            bindingMap.put("simpleClassNameUnderlineCases", simpleClassNameUnderlineCases);
            bindingMap.put("subClassNameVars", subClassNameVars);
            bindingMap.put("subSimpleClassName_strikeCases", subSimpleClassNameStrikeCases);
        }
        return bindingMap;
    }

    private Map<String, String> getTemplates(Integer frontType) {
        Map<String, String> templates = new LinkedHashMap<>();
        templates.putAll(SERVER_TEMPLATES);
        templates.putAll(FRONT_TEMPLATES.row(frontType));
        return templates;
    }

    @SuppressWarnings("unchecked")
    private String formatFilePath(String filePath, Map<String, Object> bindingMap) {
        filePath = StrUtil.replace(filePath, "${basePackage}",
                getStr(bindingMap, "basePackage").replaceAll("\\.", "/"));
        filePath = StrUtil.replace(filePath, "${classNameVar}",
                getStr(bindingMap, "classNameVar"));
        filePath = StrUtil.replace(filePath, "${simpleClassName}",
                getStr(bindingMap, "simpleClassName"));
        // sceneEnum 包含的字段
        CodegenSceneEnum sceneEnum = (CodegenSceneEnum) bindingMap.get("sceneEnum");
        filePath = StrUtil.replace(filePath, "${sceneEnum.prefixClass}", sceneEnum.getPrefixClass());
        filePath = StrUtil.replace(filePath, "${sceneEnum.basePackage}", sceneEnum.getBasePackage());
        // table 包含的字段
        CodegenTableDO table = (CodegenTableDO) bindingMap.get("table");
        filePath = StrUtil.replace(filePath, "${table.moduleName}", table.getModuleName());
        filePath = StrUtil.replace(filePath, "${table.businessName}", table.getBusinessName());
        filePath = StrUtil.replace(filePath, "${table.className}", table.getClassName());
        // 特殊：主子表专属逻辑
        Integer subIndex = (Integer) bindingMap.get("subIndex");
        if (subIndex != null) {
            CodegenTableDO subTable = ((List<CodegenTableDO>) bindingMap.get("subTables")).get(subIndex);
            filePath = StrUtil.replace(filePath, "${subTable.moduleName}", subTable.getModuleName());
            filePath = StrUtil.replace(filePath, "${subTable.businessName}", subTable.getBusinessName());
            filePath = StrUtil.replace(filePath, "${subTable.className}", subTable.getClassName());
            filePath = StrUtil.replace(filePath, "${subSimpleClassName}",
                    ((List<String>) bindingMap.get("subSimpleClassNames")).get(subIndex));
        }
        return filePath;
    }

}
