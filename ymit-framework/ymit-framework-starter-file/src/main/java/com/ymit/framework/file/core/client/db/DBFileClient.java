package com.ymit.framework.file.core.client.db;

import cn.hutool.extra.spring.SpringUtil;
import com.ymit.framework.file.core.client.AbstractFileClient;

/**
 * 基于 DB 存储的文件客户端的配置类
 *
 * @author Y.S
 * @date 2024.05.24
 */
public class DBFileClient extends AbstractFileClient<DBFileClientConfig> {
    private DBFileContentFrameworkDAO dao;

    public DBFileClient(Long id, DBFileClientConfig config) {
        super(id, config);
    }

    @Override
    protected void doInit() {
    }

    private DBFileContentFrameworkDAO getDao() {
        // 延迟获取，因为 SpringUtil 初始化太慢
        if (this.dao == null) {
            this.dao = SpringUtil.getBean(DBFileContentFrameworkDAO.class);
        }
        return this.dao;
    }

    @Override
    public String upload(byte[] content, String path, String mime) {
        this.getDao().insert(this.getId(), path, content);
        // 拼接返回路径
        //return super.formatFileUrl(config.getDomain(), path);
        return super.formatFileUrl("", path);
    }

    @Override
    public void delete(String path) {
        this.getDao().delete(this.getId(), path);
    }

    @Override
    public byte[] getContent(String path) {
        return this.getDao().selectContent(this.getId(), path);
    }
}
