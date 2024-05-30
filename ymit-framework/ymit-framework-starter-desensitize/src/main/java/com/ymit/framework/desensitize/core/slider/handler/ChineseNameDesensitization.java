package com.ymit.framework.desensitize.core.slider.handler;

import com.ymit.framework.desensitize.core.slider.annotation.ChineseNameDesensitize;

/**
 * {@link ChineseNameDesensitize} 的脱敏处理器
 *
 * @author Y.S
 * @date 2024.05.23
 */
public class ChineseNameDesensitization extends AbstractSliderDesensitizationHandler<ChineseNameDesensitize> {
    @Override
    Integer getPrefixKeep(ChineseNameDesensitize annotation) {
        return annotation.prefixKeep();
    }

    @Override
    Integer getSuffixKeep(ChineseNameDesensitize annotation) {
        return annotation.suffixKeep();
    }

    @Override
    String getReplacer(ChineseNameDesensitize annotation) {
        return annotation.replacer();
    }
}
