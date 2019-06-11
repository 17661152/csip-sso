package com.expect.csip.iam.domain;

import com.expect.common.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 用户角色关系
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@ApiModel(description = "用户角色关系信息")
@TableName("TIA_USER_ROLE_REL")
public class UserRoleRel extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色ID")
    @TableId("ROLE_ID")
    private String roleId;

    @ApiModelProperty(value = "用户ID")
    @TableField("USER_ID")
    private String userId;

    /*@ApiModelProperty(value="用户id集合")
    @TableField(exist=false)
    private List<String> userIds;

    @ApiModelProperty(value="用户信息")
    @TableField(exist=false)
    private User user;*/

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

	/*public List<String> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}*/
}
