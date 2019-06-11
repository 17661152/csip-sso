package com.expect.csip.iam.controller;


import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.security.token.SSOToken;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.expect.common.controller.BaseController;
import com.expect.common.domain.PageQueryResult;
import com.expect.common.domain.TreeNode;
import com.expect.common.domain.VueRouter;
import com.expect.csip.iam.domain.Menu;
import com.expect.csip.iam.domain.condition.MenuCondition;
import com.expect.csip.iam.service.MenuPageRelService;
import com.expect.csip.iam.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 菜单 前端控制器
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@Api(tags = "菜单微服务")
@RestController
@RequestMapping("/iam/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuPageRelService menuPageRelService;


    @ApiOperation("菜单分页列表信息")
    @ApiImplicitParam(name = "condition", value = "菜单查询条件", required = true, dataType = "MenuCondition", paramType = "body")
    @PostMapping("/listMenuPage")
    public PageQueryResult<Menu> listMenuPage(@RequestBody MenuCondition condition) {
        IPage<Menu> pageInfo = menuService.listMenuPage(condition);
        return buildPageQueryResult(pageInfo);
    }


    @ApiOperation("主键查询菜单")
    @ApiImplicitParam(name = "menuId", value = "菜单的主键", required = true, dataType = "string", paramType = "body")
    @PostMapping("/getMenuById")
    public Menu getMenuById(@RequestBody String menuId) {
        return menuService.getMenuById(menuId);
    }


    @ApiOperation("主键批量查询菜单")
    @PostMapping("/listMenuMapsByIds")
    public Map<String, Menu> listMenuMapsByIds(@ApiParam(name = "menuIds", value = "菜单的主键集合", required = true) @RequestBody List<String> menuIds) {
        return menuService.listMenuMapsByIds(menuIds);
    }

    @ApiOperation("主键删除菜单")
    @ApiImplicitParam(name = "menuId", value = "菜单的主键", required = true, dataType = "string", paramType = "body")
    @PostMapping("/deleteMenuById")
    public Boolean deleteMenuById(@RequestBody String menuId) {
        Integer total = menuPageRelService.countMenuPageRelByMenuId(menuId);
        if (total > 0) {
            throw new ApiException("请先删除菜单所关联的页面列表");
        }
        return menuService.deleteMenuById(menuId);
    }


    @ApiOperation("保存菜单")
    @ApiImplicitParam(name = "menu", value = "菜单信息", required = true, dataType = "Menu", paramType = "body")
    @PostMapping("/saveMenu")
    public Menu saveMenu(@RequestBody Menu menu) {
        return menuService.saveMenu(menu);
    }


    @ApiOperation("修改菜单")
    @ApiImplicitParam(name = "menu", value = "菜单信息", required = true, dataType = "Menu", paramType = "body")
    @PostMapping("/updateMenu")
    public Boolean updateMenu(@RequestBody Menu menu) {
        if (StringUtils.equals(menu.getMenuId(), menu.getParentMenuId())) {
            throw new ApiException("父菜单不能选择本身");
        }

        List<String> menuChilds = menuService.listMenuChildsByMenuId(menu.getMenuId());
        if (menuChilds.contains(menu.getParentMenuId())) {
            throw new ApiException("父菜单不能选择当前菜单的子菜单");
        }
        return menuService.updateMenu(menu);
    }

    @ApiOperation("权限路由菜单列表")
    @PostMapping("/listVueRouterMenu")
    public List<VueRouter> listVueRouterMenu() {
        SSOToken token = SSOHelper.attrToken(request);
        return menuService.listAuthVueRouter(token.getId());
    }

    @ApiOperation("菜单树列表")
    @PostMapping("/listMenuTree")
    public List<TreeNode> listMenuTree() {
        return menuService.listMenuTree();
    }

    @ApiOperation("查询所有菜单、页面、元素列表")
    @PostMapping("/listFuncPermsTree")
    public List<TreeNode> listFuncPermsTree() {
        return menuService.listFuncPermsTree();
    }

    @ApiOperation("菜单树下拉列表")
    @PostMapping("/listOneOrTwoMenuTreeSelect")
    public List<TreeNode> listOneOrTwoMenuTreeSelect() {
        return menuService.listOneOrTwoMenuTreeSelect();
    }

    @ApiOperation("菜单树下拉列表")
    @PostMapping("/listMenuTreeSelect")
    public List<TreeNode> listMenuTreeSelect() {
        return menuService.listMenuTreeSelect();
    }

    @ApiOperation("禁用菜单")
    @ApiImplicitParam(name = "menuId", value = "菜单ID", required = true, dataType = "string", paramType = "body")
    @PostMapping("/disableMenu")
    public Boolean disableMenu(@RequestBody String menuId) {
        return menuService.disableMenu(menuId);
    }

    @ApiOperation("启用菜单")
    @ApiImplicitParam(name = "menuId", value = "菜单ID", required = true, dataType = "string", paramType = "body")
    @PostMapping("/enableMenu")
    public Boolean enableMenu(@RequestBody String menuId) {
        return menuService.enableMenu(menuId);
    }
}
