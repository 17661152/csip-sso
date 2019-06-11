package com.expect.csip.iam.domain.condition;

import com.expect.common.domain.condition.BaseCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * <p>
 * 用户查询条件
 * </p>
 * @author jack.zeng
 * @since 2019-01-07
 */
@ApiModel(description="用户查询条件")
public class UserCondition extends BaseCondition {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="用户名")
    private String loginName;

    @ApiModelProperty(value="姓名")
    private String name;

    @ApiModelProperty(value="电话号码")
    private String phone;

    @ApiModelProperty(value="机构id")
    private String organId;

    @ApiModelProperty(value="状态")
    private String status;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
