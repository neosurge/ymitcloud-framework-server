package com.ymit.module.infra.api.file;


import com.ymit.module.infra.api.file.dto.FileRelation;
import com.ymit.module.infra.api.file.dto.FileRelationVO;

import java.util.List;

/**
 * 文件 API 接口
 *
 * @author Y.S
 * @date 2024.05.26
 */
public interface FileApi {

    /**
     * 保存文件，并返回文件的访问路径
     *
     * @param content 文件内容
     * @return 文件路径
     */
    default String createFile(byte[] content) {
        return this.createFile(null, null, content);
    }

    /**
     * 保存文件，并返回文件的访问路径
     *
     * @param path    文件路径
     * @param content 文件内容
     * @return 文件路径
     */
    default String createFile(String path, byte[] content) {
        return this.createFile(null, path, content);
    }

    /**
     * 保存文件，并返回文件的访问路径
     *
     * @param name    文件名称
     * @param path    文件路径
     * @param content 文件内容
     * @return 文件路径
     */
    String createFile(String name, String path, byte[] content);

    /**
     * 批量保存文件关系集
     *
     * @param datas 文件关系集合
     */
    List<FileRelationVO> batchSaveFileRel(String kind, Long code, List<FileRelation> datas);
}
