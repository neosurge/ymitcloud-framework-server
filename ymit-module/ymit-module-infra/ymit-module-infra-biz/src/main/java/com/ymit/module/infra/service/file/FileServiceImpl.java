package com.ymit.module.infra.service.file;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.ymit.framework.common.data.PageResult;
import com.ymit.framework.common.util.io.FileUtils;
import com.ymit.framework.file.core.client.FileClient;
import com.ymit.framework.file.core.utils.FileTypeUtils;
import com.ymit.module.infra.controller.admin.file.vo.file.FilePageReqVO;
import com.ymit.module.infra.dal.dataobject.file.FileDO;
import com.ymit.module.infra.dal.mysql.file.FileMapper;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import static com.ymit.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ymit.module.infra.enums.ErrorCodeConstants.FILE_NOT_EXISTS;

/**
 * 文件 Service 实现类
 *
 * @author 云码源码
 */
@Service
public class FileServiceImpl implements FileService {

    @Resource
    private FileConfigService fileConfigService;

    @Resource
    private FileMapper fileMapper;

    @Override
    public PageResult<FileDO> getFilePage(FilePageReqVO pageReqVO) {
        return this.fileMapper.selectPage(pageReqVO);
    }

    @Override
    @SneakyThrows
    public FileDO createFile(String name, String path, byte[] content) {
        // 计算默认的 path 名
        String mime = FileTypeUtils.getMineType(content, name);
        if (StrUtil.isEmpty(path)) {
            path = FileUtils.generatePath(content, name);
        }
        // 如果 name 为空，则使用 path 填充
        if (StrUtil.isEmpty(name)) {
            name = path;
        }

        // 上传到文件存储器
        FileClient client = this.fileConfigService.getMasterFileClient();
        Assert.notNull(client, "客户端(master) 不能为空");
        String url = client.upload(content, path, mime);

        // 保存到数据库
        FileDO file = new FileDO();
        file.setConfigId(client.getId());
        file.setName(name);
        file.setPath(path);
        file.setUrl(url);
        file.setMime(mime);
        file.setSize(content.length);
        this.fileMapper.insert(file);
        return file;
    }

    @Override
    public void deleteFile(Long id) throws Exception {
        // 校验存在
        FileDO file = this.validateFileExists(id);

        // 从文件存储器中删除
        FileClient client = this.fileConfigService.getFileClient(file.getConfigId());
        Assert.notNull(client, "客户端({}) 不能为空", file.getConfigId());
        client.delete(file.getPath());

        // 删除记录
        this.fileMapper.deleteById(id);
    }

    private FileDO validateFileExists(Long id) {
        FileDO fileDO = this.fileMapper.selectById(id);
        if (fileDO == null) {
            throw exception(FILE_NOT_EXISTS);
        }
        return fileDO;
    }

    @Override
    public byte[] getFileContent(Long configId, String path) throws Exception {
        FileClient client = this.fileConfigService.getFileClient(configId);
        Assert.notNull(client, "客户端({}) 不能为空", configId);
        return client.getContent(path);
    }

}
