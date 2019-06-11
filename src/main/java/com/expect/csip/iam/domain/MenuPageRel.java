package com.expect.csip.iam.domain;

import com.expect.common.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 菜单页面关系
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@ApiModel(description = "菜单页面关系信息")
@TableName("TIA_MENU_PAGE_REL")
public class MenuPageRel extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "页面ID")
    @TableId("PAGE_ID")
    private String pageId;

    @ApiModelProperty(value = "菜单ID")
    @TableField("MENU_ID")
    private String menuId;

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

}
