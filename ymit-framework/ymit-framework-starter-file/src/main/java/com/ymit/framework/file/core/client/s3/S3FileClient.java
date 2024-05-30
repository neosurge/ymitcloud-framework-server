package com.ymit.framework.file.core.client.s3;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.ymit.framework.file.core.client.AbstractFileClient;
import io.minio.*;

import java.io.ByteArrayInputStream;

/**
 * 基于 S3 协议的文件客户端，实现 MinIO、阿里云、腾讯云、七牛云、华为云等云服务
 * <p>
 * S3 协议的客户端，采用亚马逊提供的 software.amazon.awssdk.s3 库
 *
 * @author Y.S
 * @date 2024.05.24
 */
public class S3FileClient extends AbstractFileClient<S3FileClientConfig> {
    private MinioClient client;

    public S3FileClient(Long id, S3FileClientConfig config) {
        super(id, config);
    }

    @Override
    protected void doInit() {
        // 补全 domain
        if (StrUtil.isEmpty(this.config.getDomain())) {
            this.config.setDomain(this.buildDomain());
        }
        // 初始化客户端
        this.client = MinioClient.builder()
                .endpoint(this.buildEndpointURL()) // Endpoint URL
                .region(this.buildRegion()) // Region
                .credentials(this.config.getAccessKey(), this.config.getAccessSecret()) // 认证密钥
                .build();
    }

    @Override
    public String upload(byte[] content, String path, String mime) throws Exception {
        // 执行上传
        this.client.putObject(PutObjectArgs.builder()
                .bucket(this.config.getBucket()) // bucket 必须传递
                .contentType(mime)
                .object(path) // 相对路径作为 key
                .stream(new ByteArrayInputStream(content), content.length, -1) // 文件内容
                .build());
        // 拼接返回路径
        return this.config.getDomain() + "/" + path;
    }

    @Override
    public void delete(String path) throws Exception {
        this.client.removeObject(RemoveObjectArgs.builder()
                .bucket(this.config.getBucket()) // bucket 必须传递
                .object(path) // 相对路径作为 key
                .build());
    }

    @Override
    public byte[] getContent(String path) throws Exception {
        GetObjectResponse response = this.client.getObject(GetObjectArgs.builder()
                .bucket(this.config.getBucket()) // bucket 必须传递
                .object(path) // 相对路径作为 key
                .build());
        return IoUtil.readBytes(response);
    }

    /**
     * 基于 endpoint 构建调用云服务的 URL 地址
     *
     * @return URI 地址
     */
    private String buildEndpointURL() {
        // 如果已经是 http 或者 https，则不进行拼接.主要适配 MinIO
        if (HttpUtil.isHttp(this.config.getEndpoint()) || HttpUtil.isHttps(this.config.getEndpoint())) {
            return this.config.getEndpoint();
        }
        return StrUtil.format("https://{}", this.config.getEndpoint());
    }

    /**
     * 基于 bucket + endpoint 构建访问的 Domain 地址
     *
     * @return Domain 地址
     */
    private String buildDomain() {
        // 如果已经是 http 或者 https，则不进行拼接.主要适配 MinIO
        if (HttpUtil.isHttp(this.config.getEndpoint()) || HttpUtil.isHttps(this.config.getEndpoint())) {
            return StrUtil.format("{}/{}", this.config.getEndpoint(), this.config.getBucket());
        }
        // 阿里云、腾讯云、华为云都适合。七牛云比较特殊，必须有自定义域名
        return StrUtil.format("https://{}.{}", this.config.getBucket(), this.config.getEndpoint());
    }

    /**
     * 基于 bucket 构建 region 地区
     *
     * @return region 地区
     */
    private String buildRegion() {
        // 阿里云必须有 region，否则会报错
        if (this.config.getEndpoint().contains(S3FileClientConfig.ENDPOINT_ALIYUN)) {
            return StrUtil.subBefore(this.config.getEndpoint(), '.', false)
                    .replaceAll("-internal", "")// 去除内网 Endpoint 的后缀
                    .replaceAll("https://", "");
        }
        // 腾讯云必须有 region，否则会报错
        if (this.config.getEndpoint().contains(S3FileClientConfig.ENDPOINT_TENCENT)) {
            return StrUtil.subAfter(this.config.getEndpoint(), "cos.", false)
                    .replaceAll("." + S3FileClientConfig.ENDPOINT_TENCENT, ""); // 去除 Endpoint
        }
        return null;
    }

}
