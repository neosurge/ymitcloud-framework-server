package com.ymitcloud.framework.file.core.client.s3;

import cn.hutool.core.util.StrUtil;
import com.ymitcloud.framework.file.core.client.FileClientConfig;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;

/**
 * S3 文件客户端的配置类
 */
@Data
public class S3FileClientConfig implements FileClientConfig {

    public static final String ENDPOINT_QINIU = "qiniucs.com";
    public static final String ENDPOINT_ALIYUN = "aliyuncs.com";
    public static final String ENDPOINT_TENCENT = "myqcloud.com";

    @NotNull(message = "endpoint 不能为空")
    private String endpoint;
    @URL(message = "domain 必须是 URL 格式")
    private String domain;
    /**
     * 存储 Bucket
     */
    @NotNull(message = "bucket 不能为空")
    private String bucket;
    @NotNull(message = "accessKey 不能为空")
    private String accessKey;
    /**
     * 访问 Secret
     */
    @NotNull(message = "accessSecret 不能为空")
    private String accessSecret;

    @SuppressWarnings("RedundantIfStatement")
    @AssertTrue(message = "domain 不能为空")
    @JsonIgnore
    public boolean isDomainValid() {
        // 如果是七牛，必须带有 domain
        if (StrUtil.contains(endpoint, ENDPOINT_QINIU) && StrUtil.isEmpty(domain)) {
            return false;
        }
        return true;
    }

}
