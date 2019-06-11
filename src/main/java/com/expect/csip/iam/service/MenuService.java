package com.expect.csip.iam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.expect.common.domain.TreeNode;
import com.expect.common.domain.VueRouter;
import com.expect.csip.iam.domain.Menu;
import com.expect.csip.iam.domain.condition.MenuCondition;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 菜单 服务类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
public interface MenuService extends IService<Menu> {

    /**
     * 菜单分页列表
     *
     * @param condition
     * @return
     */
    IPage<Menu> listMenuPage(MenuCondition condition);

    /**
     * 主键查询菜单
     *
     * @param menuId
     * @return
     */
    Menu getMenuById(String menuId);

    /**
     * 主键批量查询菜单
     *
     * @param menuIds
     * @return
     */
    Map<String, Menu> listMenuMapsByIds(List<String> menuIds);

    /**
     * 主键批量删除菜单
     *
     * @param menuIds
     * @return
     */
    Boolean deleteMenuByIds(List<String> menuIds);

    /**
     * 主键删除菜单
     *
     * @param menuId
     * @return
     */
    Boolean deleteMenuById(String menuId);

    /**
     * 保存菜单
     *
     * @param menu
     * @return
     */
    Menu saveMenu(Menu menu);

    /**
     * 修改菜单
     *
     * @param menu
     * @return
     */
    Boolean updateMenu(Menu menu);

    /**
     * 菜单树
     *
     * @return
     */
    List<TreeNode> listMenuTree();

    /**
     * 菜单一、二级下拉列表树
     * @return
     */
    List<TreeNode> listOneOrTwoMenuTreeSelect();

    /**
     * 菜单下拉列表树
     * @return
     */
    List<TreeNode> listMenuTreeSelect();

    /**
     * 权限菜单列表
     *
     * @param userId
     * @return
     */
    List<VueRouter> listAuthVueRouter(String userId);

    /**
     * 查找当前菜单下的子菜单ID列表
     * @param menuId
     * @return
     */
    List<String> listMenuChildsByMenuId(String menuId);

    /**
     * 禁用菜单
     * @param menuId
     * @return
     */
    Boolean disableMenu(String menuId);

    /**
     * 启用
     * @param menuId
     * @return
     */
    Boolean enableMenu(String menuId);

    /**
     * 查询所有菜单、页面、元素列表
     *
     * @return
     */
    List<TreeNode> listFuncPermsTree();
}
