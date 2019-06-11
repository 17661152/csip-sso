package com.expect.csip.iam.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.expect.common.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 角色数据权限关系
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@ApiModel(description = "角色数据权限关系信息")
@TableName("TIA_ROLE_DATA_PERM_REL")
public class RoleDataPermRel extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色ID")
    @TableId(value = "ROLE_ID", type = IdType.INPUT)
    private String roleId;

    @ApiModelProperty(value = "数据权限ID")
    @TableField("DATA_PERM_ID")
    private String dataPermId;

    @ApiModelProperty(value = "数据权限值ID")
    @TableField("DATA_PERM_VALUE_ID")
    private String dataPermValueId;

    @TableField(exist = false)
    private String accessAddress;

    @TableField(exist = false)
    private String conditionExpression;

    @TableField(exist = false)
    private String menuId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getDataPermId() {
        return dataPermId;
    }

    public void setDataPermId(String dataPermId) {
        this.dataPermId = dataPermId;
    }

    public String getDataPermValueId() {
        return dataPermValueId;
    }

    public void setDataPermValueId(String dataPermValueId) {
        this.dataPermValueId = dataPermValueId;
    }

    public String getAccessAddress() {
        return accessAddress;
    }

    public void setAccessAddress(String accessAddress) {
        this.accessAddress = accessAddress;
    }

    public String getConditionExpression() {
        return conditionExpression;
    }

    public void setConditionExpression(String conditionExpression) {
        this.conditionExpression = conditionExpression;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
