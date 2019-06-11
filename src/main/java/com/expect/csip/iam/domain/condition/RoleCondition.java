package com.expect.csip.iam.domain.condition;

import com.expect.common.domain.condition.BaseCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * <p>
 * 角色查询条件
 * </p>
 * @author jack.zeng
 * @since 2019-01-07
 */
@ApiModel(description="角色查询条件")
public class RoleCondition extends BaseCondition {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="角色名称")
    private String roleName;

    @ApiModelProperty(value="角色编码")
    private String roleCode;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
}
