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
import java.util.List;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@ApiModel(description = "角色信息")
@TableName("TIA_ROLE")
public class Role extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色ID")
    @TableId("ROLE_ID")
    private String roleId;

    @ApiModelProperty(value = "角色编码")
    @TableField("ROLE_CODE")
    private String roleCode;

    @ApiModelProperty(value = "角色名称")
    @TableField("ROLE_NAME")
    private String roleName;

    @ApiModelProperty(value = "描述")
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

    @ApiModelProperty(value = "删除标记：0-无效记录（已删除）；1-有效记录（未删除）；")
    @TableField("DELETE_FLAG")
    @TableLogic
    private String deleteFlag;

    @ApiModelProperty(value = "角色数据权限列表")
    @TableField(exist = false)
    private List<RoleDataPermRel> roleDataPermRels;

    @ApiModelProperty(value = "角色功能权限列表")
    @TableField(exist = false)
    private List<RoleFuncPermRel> roleFuncPermRels;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public List<RoleDataPermRel> getRoleDataPermRels() {
        return roleDataPermRels;
    }

    public void setRoleDataPermRels(List<RoleDataPermRel> roleDataPermRels) {
        this.roleDataPermRels = roleDataPermRels;
    }

    public List<RoleFuncPermRel> getRoleFuncPermRels() {
        return roleFuncPermRels;
    }

    public void setRoleFuncPermRels(List<RoleFuncPermRel> roleFuncPermRels) {
        this.roleFuncPermRels = roleFuncPermRels;
    }
}
