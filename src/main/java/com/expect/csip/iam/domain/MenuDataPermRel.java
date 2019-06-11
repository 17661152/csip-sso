package com.expect.csip.iam.domain;

import com.expect.common.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 菜单数据权限关系
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@ApiModel(description = "菜单数据权限关系信息")
@TableName("TIA_MENU_DATA_PERM_REL")
public class MenuDataPermRel extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据权限ID")
    @TableId("DATA_PERM_ID")
    private String dataPermId;

    @ApiModelProperty(value = "菜单ID")
    @TableField("MENU_ID")
    private String menuId;

    public String getDataPermId() {
        return dataPermId;
    }

    public void setDataPermId(String dataPermId) {
        this.dataPermId = dataPermId;
    }
    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

}
