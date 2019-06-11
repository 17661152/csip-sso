package com.expect.csip.iam.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.expect.common.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * <p>
 * 功能权限
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@ApiModel(description = "功能权限信息")
@TableName("TIA_FUNC_PERM")
public class FuncPerm extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "功能权限ID")
    @TableId("FUNC_PERM_ID")
    private String funcPermId;

    @ApiModelProperty(value = "名称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "类型：1-菜单；2-页面；3-元素")
    @TableField("TYPE")
    private String type;

    @ApiModelProperty(value = "是否启用：0-禁用；1-启用；")
    @TableField("IS_ENABLE")
    private String isEnable;

    @ApiModelProperty(value = "排序号码：用于显示顺序")
    @TableField("SORT_NUMBER")
    private Integer sortNumber;

    @ApiModelProperty(value = "说明")
    @TableField("DESCR")
    private String descr;

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

    public String getFuncPermId() {
        return funcPermId;
    }

    public void setFuncPermId(String funcPermId) {
        this.funcPermId = funcPermId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
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
}
