package com.ymit.framework.xss.core.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.ymit.framework.xss.core.clean.XssCleaner;

import java.io.IOException;
import java.io.Serial;

/**
 * XSS 过滤 jackson 反序列化器。
 * 在反序列化的过程中，会对字符串进行 XSS 过滤。
 *
 * @author Y.S
 * @date 2024.05.20
 */
public class XssStringJsonDeserializer extends StringDeserializer {
    @Serial
    private static final long serialVersionUID = 1L;
    private final XssCleaner xssCleaner;

    public XssStringJsonDeserializer(XssCleaner xssCleaner) {
        this.xssCleaner = xssCleaner;
    }

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.hasToken(JsonToken.VALUE_STRING)) {
            return this.xssCleaner.clean(p.getText());
        }
        JsonToken t = p.currentToken();
        // [databind#381]
        if (t == JsonToken.START_ARRAY) {
            return this._deserializeFromArray(p, ctxt);
        }
        // need to gracefully handle byte[] data, as base64
        if (t == JsonToken.VALUE_EMBEDDED_OBJECT) {
            Object ob = p.getEmbeddedObject();
            if (ob == null) {
                return null;
            }
            if (ob instanceof byte[]) {
                return ctxt.getBase64Variant().encode((byte[]) ob, false);
            }
            // otherwise, try conversion using toString()...
            return ob.toString();
        }
        // 29-Jun-2020, tatu: New! "Scalar from Object" (mostly for XML)
        if (t == JsonToken.START_OBJECT) {
            return ctxt.extractScalarFromObject(p, this, this._valueClass);
        }
        if (t.isScalarValue()) {
            String text = p.getValueAsString();
            return this.xssCleaner.clean(text);
        }
        return (String) ctxt.handleUnexpectedToken(this._valueClass, p);
    }
}
