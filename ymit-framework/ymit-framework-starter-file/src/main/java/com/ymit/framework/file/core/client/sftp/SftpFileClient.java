package com.ymit.framework.file.core.client.sftp;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.ssh.Sftp;
import com.ymit.framework.common.util.io.FileUtils;
import com.ymit.framework.file.core.client.AbstractFileClient;

import java.io.File;

/**
 * Sftp 文件客户端
 *
 * @author Y.S
 * @date 2024.05.24
 */
public class SftpFileClient extends AbstractFileClient<SftpFileClientConfig> {
    private Sftp sftp;

    public SftpFileClient(Long id, SftpFileClientConfig config) {
        super(id, config);
    }

    @Override
    protected void doInit() {
        // 补全风格。例如说 Linux 是 /，Windows 是 \
        if (!this.config.getBasePath().endsWith(File.separator)) {
            this.config.setBasePath(this.config.getBasePath() + File.separator);
        }
        // 初始化 Ftp 对象
        this.sftp = new Sftp(this.config.getHost(), this.config.getPort(), this.config.getUsername(), this.config.getPassword());
    }

    @Override
    public String upload(byte[] content, String path, String mime) throws Exception {
        // 执行写入
        String filePath = this.getFilePath(path);
        File file = FileUtils.createTempFile(content);
        this.sftp.upload(filePath, file);
        // 拼接返回路径
        return super.formatFileUrl(this.config.getDomain(), path);
    }

    @Override
    public void delete(String path) {
        String filePath = this.getFilePath(path);
        this.sftp.delFile(filePath);
    }

    @Override
    public byte[] getContent(String path) throws Exception {
        String filePath = this.getFilePath(path);
        File destFile = FileUtils.createTempFile();
        this.sftp.download(filePath, destFile);
        return FileUtil.readBytes(destFile);
    }

    private String getFilePath(String path) {
        return this.config.getBasePath() + path;
    }
}
