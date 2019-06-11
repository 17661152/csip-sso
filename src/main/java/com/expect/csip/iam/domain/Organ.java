package com.expect.csip.iam.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.expect.common.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * <p>
 * 组织机构
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@ApiModel(description = "组织机构信息")
@TableName("TIA_ORGAN")
public class Organ extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "组织ID")
    @TableId("ORGAN_ID")
    private String organId;

    @ApiModelProperty(value = "父组织ID")
    @TableField("PARENT_ORGAN_ID")
    private String parentOrganId;

    @ApiModelProperty(value = "简称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "全称")
    @TableField("FULL_NAME")
    private String fullName;

    @ApiModelProperty(value = "简称路径：从组织根简称到本组织简称的路径，包含本组织简称。")
    @TableField("NAME_PATH")
    private String namePath;

    @ApiModelProperty(value = "ID路径")
    @TableField("ID_PATH")
    private String idPath;

    @ApiModelProperty(value = "是否叶结点")
    @TableField("IS_LEAF_NODE")
    private String isLeafNode;

    @ApiModelProperty(value = "树层级")
    @TableField("TREE_LEVEL")
    private Integer treeLevel;

    @ApiModelProperty(value = "排序号码")
    @TableField("SORT_NUMBER")
    private Integer sortNumber;

    @ApiModelProperty(value = "类型：1-公司；2-部门；")
    @TableField("TYPE")
    private String type;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_PERSON", fill = FieldFill.INSERT)
    private String createPerson;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "最后更新人")
    @TableField(value = "LAST_UPDATE_PERSON", fill = FieldFill.INSERT_UPDATE)
    private String lastUpdatePerson;

    @ApiModelProperty(value = "最后更新时间")
    @TableField(value = "LAST_UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date lastUpdateTime;

    @ApiModelProperty(value = "删除标记：0-无效记录（已删除）；1-有效记录（未删除）；")
    @TableField("DELETE_FLAG")
    @TableLogic
    private String deleteFlag;


    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    public String getParentOrganId() {
        return parentOrganId;
    }

    public void setParentOrganId(String parentOrganId) {
        this.parentOrganId = parentOrganId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNamePath() {
        return namePath;
    }

    public void setNamePath(String namePath) {
        this.namePath = namePath;
    }

    public String getIdPath() {
        return idPath;
    }

    public void setIdPath(String idPath) {
        this.idPath = idPath;
    }

    public String getIsLeafNode() {
        return isLeafNode;
    }

    public void setIsLeafNode(String isLeafNode) {
        this.isLeafNode = isLeafNode;
    }

    public Integer getTreeLevel() {
        return treeLevel;
    }

    public void setTreeLevel(Integer treeLevel) {
        this.treeLevel = treeLevel;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdatePerson() {
        return lastUpdatePerson;
    }

    public void setLastUpdatePerson(String lastUpdatePerson) {
        this.lastUpdatePerson = lastUpdatePerson;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

}
