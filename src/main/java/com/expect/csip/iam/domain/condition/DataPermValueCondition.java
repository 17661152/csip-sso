package com.expect.csip.iam.domain.condition;

import com.expect.common.domain.condition.BaseCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * <p>
 * 数据权限值查询条件
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@ApiModel(description = "数据权限值查询条件")
public class DataPermValueCondition extends BaseCondition {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据权限ID")
    private String dataPermId;

    @ApiModelProperty(value = "菜单ID")
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
