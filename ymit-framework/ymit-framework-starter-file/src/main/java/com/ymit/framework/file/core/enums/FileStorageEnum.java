package com.ymit.framework.file.core.enums;

import cn.hutool.core.util.ArrayUtil;
import com.ymit.framework.file.core.client.FileClient;
import com.ymit.framework.file.core.client.FileClientConfig;
import com.ymit.framework.file.core.client.db.DBFileClient;
import com.ymit.framework.file.core.client.db.DBFileClientConfig;
import com.ymit.framework.file.core.client.ftp.FtpFileClient;
import com.ymit.framework.file.core.client.ftp.FtpFileClientConfig;
import com.ymit.framework.file.core.client.local.LocalFileClient;
import com.ymit.framework.file.core.client.local.LocalFileClientConfig;
import com.ymit.framework.file.core.client.s3.S3FileClient;
import com.ymit.framework.file.core.client.s3.S3FileClientConfig;
import com.ymit.framework.file.core.client.sftp.SftpFileClient;
import com.ymit.framework.file.core.client.sftp.SftpFileClientConfig;

/**
 * 文件存储器枚举
 *
 * @author Y.S
 * @date 2024.05.24
 */
public enum FileStorageEnum {
    DB(1, DBFileClientConfig.class, DBFileClient.class),
    LOCAL(10, LocalFileClientConfig.class, LocalFileClient.class),
    FTP(11, FtpFileClientConfig.class, FtpFileClient.class),
    SFTP(12, SftpFileClientConfig.class, SftpFileClient.class),
    S3(20, S3FileClientConfig.class, S3FileClient.class),
    ;
    /**
     * 存储器
     */
    private final Integer storage;
    /**
     * 配置类
     */
    private final Class<? extends FileClientConfig> configClass;
    /**
     * 客户端类
     */
    private final Class<? extends FileClient> clientClass;

    FileStorageEnum(Integer storage, Class<? extends FileClientConfig> configClass, Class<? extends FileClient> clientClass) {
        this.storage = storage;
        this.configClass = configClass;
        this.clientClass = clientClass;
    }

    public static FileStorageEnum getByStorage(Integer storage) {
        return ArrayUtil.firstMatch(o -> o.getStorage().equals(storage), values());
    }

    public Integer getStorage() {
        return this.storage;
    }

    public Class<? extends FileClientConfig> getConfigClass() {
        return this.configClass;
    }

    public Class<? extends FileClient> getClientClass() {
        return this.clientClass;
    }
}
