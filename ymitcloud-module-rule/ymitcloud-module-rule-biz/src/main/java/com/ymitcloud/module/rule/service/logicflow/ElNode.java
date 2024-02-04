package com.ymitcloud.module.rule.service.logicflow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.ymitcloud.framework.common.exception.ServiceException;

import lombok.Getter;
import lombok.Setter;

/**
 * EL节点表达语言
 */
@Getter
@Setter
public class ElNode implements Serializable {
    private static final long serialVersionUID = 2658844486300573885L;
    /**
     * 类型,必填
     *
     * @see ElTypeEnum
     */
    String type;
    /**
     * 名字, type 是elType 时，必填
     *
     * @see ElNameEnum
     */
    String name;
    /**
     * 条件节点id
     */
    String conditionNodeId;
    /**
     * 别名节点id
     */
    String aliasNodeId;
    /**
     * 数据
     */
    String data;
    /**
     * 标签tag
     */
    String tag;
    /**
     * 节点id, type 是 idType 时，必填
     */
    String nodeId;
    /**
     * 子节点, type 是elType 时，必填
     */
    List<ElNode> child = new ArrayList<>();

    /**
     * elname枚举
     * 支持 THEN, SWITCH, IF, WHEN
     * 不支持 FOR, WHILE, BREAK
     */
    public enum ElNameEnum {
        COMMON, THEN, WHEN, SWITCH, IF, FOR, WHILE, BREAK;
    }

    /**
     * eltype枚举
     *
     */
    public enum ElTypeEnum {
        idType, elType;
    }

    public static ElNode initThenNode() {
        ElNode elNode = new ElNode();
        elNode.setType(ElTypeEnum.elType.name());
        elNode.setName(ElNameEnum.THEN.name());
        return elNode;
    }

    public static ElNode initWhenNode() {
        ElNode elNode = new ElNode();
        elNode.setType(ElTypeEnum.elType.name());
        elNode.setName(ElNameEnum.WHEN.name());
        return elNode;
    }

    /**
     * 添加孩子节点
     *
     * @param node 节点
     */
    public void addChild(ElNode node) {
        child.add(node);
    }

    /**
     * 生成EL表达式字符串
     *
     * @return {@link String}
     */
    public String generateEl() {
        return this.getElString(this);
    }

    /**
     * 验证
     *
     * @param elNode el节点
     * @return {@link Boolean}
     */
    public Boolean validate(ElNode elNode) {
        String type = elNode.getType();
        String name = elNode.getName();
        String nodeId = elNode.getNodeId();
        String conditionNodeId = elNode.getConditionNodeId();
        List<ElNode> children = elNode.getChild();
        if (StringUtils.isEmpty(type)) {
            throw new ServiceException(99999,"type 不能为空");
        }
        if (ElTypeEnum.idType.name().equals(type) && StringUtils.isEmpty(nodeId)) {
            throw new ServiceException(99999,"type 是 idType ，必填节点id");
        }

        if (ElTypeEnum.elType.name().equals(type)) {
            if (CollectionUtils.isEmpty(children)) {
                throw new ServiceException(99999,"type 是 el表达式 时，必填子节点");
            }
            if (StringUtils.isEmpty(name)) {
                throw new ServiceException(99999,"type 是 el表达式 时，必填 name");
            }
            List<String> supportName = Arrays.asList(ElNameEnum.THEN.name(), ElNameEnum.SWITCH.name(), ElNameEnum.IF.name(), ElNameEnum.WHEN.name());
            if (!supportName.contains(name)) {
                throw new ServiceException(99999,"仅支持 THEN/SWITCH/IF/WHEN");
            }
            if (ElNameEnum.SWITCH.name().equals(name) || ElNameEnum.IF.name().equals(name)) {
                if (StringUtils.isEmpty(conditionNodeId)) {
                    throw new ServiceException(99999,"SWITCH/IF 组件里需要 conditionNodeId ");
                }
            }
        }
        return true;
    }

    /**
     * 生成EL表达式字符串
     *
     * @param elNode el节点
     * @return {@link String}
     */
    private String getElString(ElNode elNode) {
        String name = elNode.getName();
        String conditionNodeId = elNode.getConditionNodeId();
        String aliasNodeId = elNode.getAliasNodeId();
        String data = elNode.getData();
        String nodeId = elNode.getNodeId();
        String type = elNode.getType();
        String tag = elNode.getTag();
        List<ElNode> children = elNode.getChild();
        // 校验参数
        this.validate(elNode);
        String elStr = nodeId;
        if (ElTypeEnum.elType.name().equals(type)) {
            //遍历子节点，获取EL字符串
            List<String> list = new ArrayList<>();
            for (ElNode child : children) {
                //校验子节点
                this.validate(child);
                String elString;
                if (ElTypeEnum.elType.name().equals(child.getType())) {
                    elString = this.getElString(child);
                } else {
                    elString = this.doWithSuffix(child.getNodeId(), child.getAliasNodeId(), child.getData(), child.getTag(), child.getName());
                }
                if (!StringUtils.isEmpty(elString)) {
                    list.add(elString);
                }
            }
            elStr = this.elOperate(name, conditionNodeId, list, tag);
        }

        return this.doWithSuffix(elStr, aliasNodeId, data, tag, name);
    }

    /**
     * 根据不同el操作,拼接EL需要的字符串
     *
     * @param name            名字
     * @param conditionNodeId 条件节点id
     * @param params          参数个数
     * @return {@link String}
     */
    private String elOperate(String name, String conditionNodeId, List<String> params, String tag) {

        String elStr = "";
        if (CollectionUtils.isEmpty(params)) {
            return elStr;
        }
        String join = String.join(",", params);

        if (ElNameEnum.THEN.name().equals(name) || ElNameEnum.WHEN.name().equals(name)) {
            elStr = String.format("%s(%s)", name, join);
        }
        if (ElNameEnum.SWITCH.name().equals(name)) {
            elStr = String.format("%s(%s).TO(%s)", name, conditionNodeId, join);
        }
        if (ElNameEnum.IF.name().equals(name)) {
            if (!StringUtils.isEmpty(tag)) {
                conditionNodeId = String.format("%s.tag(\"%s\")", conditionNodeId, tag);
            }
            elStr = String.format("%s(%s,%s)", name, conditionNodeId, join);
        }
        return elStr;
    }


    /**
     * 后缀处理，id和data
     *
     * @param elString    el字符串
     * @param aliasNodeId 别名节点id
     * @param data        数据
     * @return {@link String}
     */
    private String doWithSuffix(String elString, String aliasNodeId, String data, String tag, String name) {
        if (StringUtils.isEmpty(elString)) {
            return "";
        }
        if (!StringUtils.isEmpty(data)) {
            elString = String.format("%s.data(%s)", elString, data);
        }
        if (!StringUtils.isEmpty(tag) && !ElNameEnum.IF.name().equals(name)) {
            elString = String.format("%s.tag(\"%s\")", elString, tag);
        }
        if (!StringUtils.isEmpty(aliasNodeId)) {
            elString = String.format("%s.id(\"%s\")", elString, aliasNodeId);
        }
        return elString;
    }
}
