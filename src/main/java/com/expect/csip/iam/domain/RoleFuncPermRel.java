package com.expect.csip.iam.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.expect.common.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 角色功能权限关系
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@ApiModel(description = "角色功能权限关系信息")
@TableName("TIA_ROLE_FUNC_PERM_REL")
public class RoleFuncPermRel extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色ID")
    @TableId("ROLE_ID")
    private String roleId;

    @ApiModelProperty(value = "功能权限ID")
    @TableField("FUNC_PERM_ID")
    private String funcPermId;

    @ApiModelProperty(value = "是否半选中状态（供tree控件）0：否，1：是")
    @TableField("HALF_CHECKED")
    private String halfChecked;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getFuncPermId() {
        return funcPermId;
    }

    public void setFuncPermId(String funcPermId) {
        this.funcPermId = funcPermId;
    }

    public String getHalfChecked() {
        return halfChecked;
    }

    public void setHalfChecked(String halfChecked) {
        this.halfChecked = halfChecked;
    }
}
