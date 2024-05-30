package com.ymit.framework.file.core.client.local;

import cn.hutool.core.io.FileUtil;
import com.ymit.framework.file.core.client.AbstractFileClient;

import java.io.File;

/**
 * 本地文件客户端
 *
 * @author Y.S
 * @date 2024.05.24
 */
public class LocalFileClient extends AbstractFileClient<LocalFileClientConfig> {
    public LocalFileClient(Long id, LocalFileClientConfig config) {
        super(id, config);
    }

    @Override
    protected void doInit() {
        // 补全风格。例如说 Linux 是 /，Windows 是 \
        if (!this.config.getBasePath().endsWith(File.separator)) {
            this.config.setBasePath(this.config.getBasePath() + File.separator);
        }
    }

    @Override
    public String upload(byte[] content, String path, String mime) {
        // 执行写入
        String filePath = this.getFilePath(path);
        FileUtil.writeBytes(content, filePath);
        // 拼接返回路径
        //return super.formatFileUrl(config.getDomain(), path);
        return super.formatFileUrl("", path);
    }

    @Override
    public void delete(String path) {
        String filePath = this.getFilePath(path);
        FileUtil.del(filePath);
    }

    @Override
    public byte[] getContent(String path) {
        String filePath = this.getFilePath(path);
        return FileUtil.readBytes(filePath);
    }

    private String getFilePath(String path) {
        return this.config.getBasePath() + path;
    }
}
