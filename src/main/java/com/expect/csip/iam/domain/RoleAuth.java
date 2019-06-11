package com.expect.csip.iam.domain;

import com.expect.common.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "角色授权信息")
public class RoleAuth extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色ID")
    private String roleId;

    @ApiModelProperty(value = "功能权限ID集合")
    private List<String> funcPermIds;

    @ApiModelProperty(value = "半选中功能权限ID集合")
    private List<String> halfFuncPermIds;

    @ApiModelProperty(value = "数据权限值ID集合")
    private List<String> dataPermValueIds;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }


    public List<String> getFuncPermIds() {
        return funcPermIds;
    }

    public void setFuncPermIds(List<String> funcPermIds) {
        this.funcPermIds = funcPermIds;
    }

    public List<String> getHalfFuncPermIds() {
        return halfFuncPermIds;
    }

    public void setHalfFuncPermIds(List<String> halfFuncPermIds) {
        this.halfFuncPermIds = halfFuncPermIds;
    }

    public List<String> getDataPermValueIds() {
        return dataPermValueIds;
    }

    public void setDataPermValueIds(List<String> dataPermValueIds) {
        this.dataPermValueIds = dataPermValueIds;
    }
}
