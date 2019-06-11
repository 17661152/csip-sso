package com.expect.csip.iam.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.expect.common.constant.PublicConstant;
import com.expect.common.domain.TreeNode;
import com.expect.common.domain.VueRouter;
import com.expect.common.domain.VueRouterMeta;
import com.expect.common.enums.FuncEnum;
import com.expect.common.enums.PublicEnum;
import com.expect.common.utils.TreeUtil;
import com.expect.csip.iam.domain.FuncPerm;
import com.expect.csip.iam.domain.Menu;
import com.expect.csip.iam.domain.Page;
import com.expect.csip.iam.domain.condition.MenuCondition;
import com.expect.csip.iam.mapper.MenuMapper;
import com.expect.csip.iam.service.FuncPermService;
import com.expect.csip.iam.service.FuncPermSvcRelService;
import com.expect.csip.iam.service.MenuDataPermRelService;
import com.expect.csip.iam.service.MenuPageRelService;
import com.expect.csip.iam.service.MenuService;
import com.expect.csip.iam.service.PageService;
import com.expect.csip.iam.service.RoleFuncPermRelService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 菜单 服务实现类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private FuncPermService funcPermService;

    @Autowired
    private PageService pageService;

    @Autowired
    private RoleFuncPermRelService roleFuncPermRelService;

    @Autowired
    private MenuDataPermRelService menuDataPermRelService;

    @Autowired
    private MenuPageRelService menuPageRelService;

    @Autowired
    private FuncPermSvcRelService funcPermSvcRelService;

    @Override
    public IPage<Menu> listMenuPage(MenuCondition condition) {
        IPage<Menu> page = condition.buildPage();
        page = baseMapper.listMenuPage(page, condition);
        return page;
    }

    @Override
    public Menu getMenuById(String menuId) {
        Menu menu = getById(menuId);
        if (null != menu) {
            if (StringUtils.isNotBlank(menu.getPageId())) {
                Page page = pageService.getPageById(menu.getPageId());
                menu.setAccessAddress(page.getAccessAddress());
                menu.setComponent(page.getComponent());
                menu.setIsInternal(page.getIsInternal());
                menu.setPageName(page.getName());
            }
            FuncPerm funcPerm = funcPermService.getFuncPermById(menu.getMenuId());
            menu.setName(funcPerm.getName());
            menu.setFuncPermId(funcPerm.getFuncPermId());
            menu.setIsEnable(funcPerm.getIsEnable());
            menu.setType(funcPerm.getType());
            menu.setSortNumber(funcPerm.getSortNumber());
            menu.setDescr(funcPerm.getDescr());
            menu.setDataPerms(menuDataPermRelService.listMenuDataPerms(menu.getMenuId()));
            menu.setServices(funcPermSvcRelService.listFuncPermService(menu.getMenuId()));
        }
        return menu;
    }

    @Override
    public Map<String, Menu> listMenuMapsByIds(List<String> menuIds) {
        Map<String, Menu> resultMap = Maps.newLinkedHashMap();
        Collection<Menu> domains = listByIds(menuIds);
        if (CollectionUtils.isEmpty(domains)) {
            return resultMap;
        }

        for (Menu menu : domains) {
            resultMap.put(menu.getMenuId(), menu);
        }
        return resultMap;
    }

    @Override
    @Transactional
    public Boolean deleteMenuByIds(List<String> menuIds) {
        return removeByIds(menuIds);
    }

    @Override
    @Transactional
    public Boolean deleteMenuById(String menuId) {
        List<String> menuChildIds = Lists.newArrayList();
        List<String> menuIds = Lists.newArrayList();
        menuIds.add(menuId);

        addMenuChildsByParentMenuId(menuChildIds, menuId);
        menuIds.addAll(menuChildIds);
        funcPermService.deleteFuncPermByIds(menuIds);
        //funcPermSvcRelService.deleteFuncPermSvcRelByFuncPermIds(menuIds);
        menuPageRelService.deleteMenuPageRelByMenuIds(menuIds);
        menuDataPermRelService.deleteMenuDataPermRelByMenuIds(menuIds);
        roleFuncPermRelService.deleteRoleFuncPermRelByFuncPermIds(menuIds);
        return removeByIds(menuIds);
    }

    @Override
    @Transactional
    public Menu saveMenu(Menu menu) {
        //先保存功能权限
        FuncPerm funcPerm = BeanUtil.toBean(menu, FuncPerm.class);
        funcPerm.setType(FuncEnum.MENU.getType());
        funcPerm.setIsEnable(PublicEnum.ACTIVE.getStatus());
        funcPerm = funcPermService.saveFuncPerm(funcPerm);
        //再保存菜单
        menu.setMenuId(funcPerm.getFuncPermId());
        List<Menu> parentMenus = Lists.newArrayList();
        addMenuParentsByParentMenuId(parentMenus, menu.getParentMenuId());
        menu.setCodePath(appendCodePath(parentMenus, menu.getMenuCode()));
        menu.setIdPath(appendIdPath(parentMenus, funcPerm.getFuncPermId()));
        menu.setNamePath(appendNamePath(parentMenus, funcPerm.getName()));
        if (StringUtils.isBlank(menu.getParentMenuId())) {
            menu.setParentMenuId(PublicEnum.TREE_ROOT.getStatus());
            menu.setTreeLevel(0);
        } else {
            int size = parentMenus.size();
            menu.setTreeLevel(size);
        }
        save(menu);
        //保存菜单数据权限关系
        menuDataPermRelService.saveMenuDataPermRel(menu.getMenuId(), menu.getDataPerms());
        //保存菜单服务关系
        //funcPermSvcRelService.saveFuncPermSvcRel(menu.getMenuId(), menu.getServices());
        return menu;
    }

    @Override
    @Transactional
    public Boolean updateMenu(Menu menu) {
        FuncPerm funcPerm = BeanUtil.toBean(menu, FuncPerm.class);
        funcPerm.setFuncPermId(menu.getMenuId());
        funcPerm.setType(FuncEnum.MENU.getType());
        funcPermService.updateFuncPerm(funcPerm);

        List<Menu> parentMenus = Lists.newArrayList();
        addMenuParentsByParentMenuId(parentMenus, menu.getParentMenuId());
        menu.setCodePath(appendCodePath(parentMenus, menu.getMenuCode()));
        menu.setIdPath(appendIdPath(parentMenus, funcPerm.getFuncPermId()));
        menu.setNamePath(appendNamePath(parentMenus, funcPerm.getName()));
        if (StringUtils.isBlank(menu.getParentMenuId())) {
            menu.setParentMenuId(PublicEnum.TREE_ROOT.getStatus());
            menu.setTreeLevel(0);
        } else {
            int size = parentMenus.size();
            menu.setTreeLevel(size);
        }
        //保存菜单数据权限关系
        menuDataPermRelService.saveMenuDataPermRel(menu.getMenuId(), menu.getDataPerms());
        //保存菜单服务关系
        //funcPermSvcRelService.saveFuncPermSvcRel(menu.getMenuId(), menu.getServices());
        return updateById(menu);
    }

    @Override
    @Transactional
    public Boolean disableMenu(String menuId) {
        List<String> menuChildIds = Lists.newArrayList();
        menuChildIds.add(menuId);
        addMenuChildsByParentMenuId(menuChildIds, menuId);
        return funcPermService.disableFuncPerm(menuChildIds);
    }

    @Override
    @Transactional
    public Boolean enableMenu(String menuId) {
        List<String> menuChildIds = Lists.newArrayList();
        menuChildIds.add(menuId);
        addMenuChildsByParentMenuId(menuChildIds, menuId);
        return funcPermService.enableFuncPerm(menuChildIds);
    }

    @Override
    public List<VueRouter> listAuthVueRouter(String userId) {
        if (StringUtils.equals(userId, PublicConstant.UNAUTHORIZED_USER_ID)) {
            return listVueRouterMenuData(null);
        }
        return listVueRouterMenuData(userId);
    }

    @Override
    public List<String> listMenuChildsByMenuId(String menuId) {
        List<String> menuAllIds = Lists.newArrayList();
        addMenuChildsByParentMenuId(menuAllIds, menuId);
        return menuAllIds;
    }

    @Override
    public List<TreeNode> listMenuTree() {
        List<TreeNode> treeNodes = Lists.newArrayList();
        List<Menu> menus = baseMapper.listMenu(null);
        if (CollectionUtils.isNotEmpty(menus)) {
            menus.forEach(menu -> {
                TreeNode treeNode = new TreeNode();
                treeNode.setKey(menu.getMenuId());
                treeNode.setTitle(menu.getName());
                treeNode.setParentId(menu.getParentMenuId());
                treeNodes.add(treeNode);
            });
        }
        return TreeUtil.buildVueTreeByRecursive(treeNodes);
    }

    @Override
    public List<TreeNode> listFuncPermsTree() {
        List<TreeNode> treeNodes = baseMapper.listFuncPerms();
        return TreeUtil.buildVueTreeByRecursive(treeNodes);
    }

    @Override
    public List<TreeNode> listMenuTreeSelect() {
        List<TreeNode> treeNodes = Lists.newArrayList();
        List<Menu> menus = baseMapper.listMenu(null);
        if (CollectionUtils.isNotEmpty(menus)) {
            menus.forEach(menu -> {
                TreeNode treeNode = new TreeNode();
                treeNode.setId(menu.getMenuId());
                treeNode.setValue(menu.getMenuId());
                treeNode.setLabel(menu.getName());
                treeNode.setpId(menu.getParentMenuId());
                treeNode.setName(menu.getCodePath());
                treeNodes.add(treeNode);
            });
        }
        return treeNodes;
    }

    @Override
    public List<TreeNode> listOneOrTwoMenuTreeSelect() {
        List<TreeNode> treeNodes = Lists.newArrayList();
        List<Menu> menus = baseMapper.listOneOrTwoMenu();
        if (CollectionUtils.isNotEmpty(menus)) {
            menus.forEach(menu -> {
                TreeNode treeNode = new TreeNode();
                treeNode.setId(menu.getMenuId());
                treeNode.setValue(menu.getMenuId());
                treeNode.setLabel(menu.getName());
                treeNode.setpId(menu.getParentMenuId());
                treeNodes.add(treeNode);
            });
        }
        return treeNodes;
    }

    /**
     * 递归遍历菜单
     *
     * @param userId
     * @return
     */
    private List<VueRouter> listVueRouterMenuData(String userId) {
        List<VueRouter> resultMenus = Lists.newArrayList();
        List<Menu> menus = baseMapper.listAuthVueRouterMenu(userId);
        if (CollectionUtils.isEmpty(menus)) {
            return resultMenus;
        }

        VueRouter vueRouter = null;
        VueRouterMeta vueRouterMeta = null;
        for (Menu menu : menus) {
            vueRouter = new VueRouter();
            vueRouter.setId(menu.getMenuId());
            vueRouter.setParentId(menu.getParentMenuId());
            vueRouter.setName(menu.getMenuCode());
            vueRouter.setPath("/" + menu.getCodePath());
            vueRouter.setComponent(menu.getLayout());
            //如果是访问内部页面
            if (StringUtils.equals(menu.getIsInternal(), PublicEnum.YES.getStatus())
                    && StringUtils.isNotBlank(menu.getAccessAddress())) {
                vueRouter.setComponent(menu.getComponent());
                vueRouter.setPath(menu.getAccessAddress());
            } else if (StringUtils.equals(menu.getIsInternal(), PublicEnum.NO.getStatus())
                    && StringUtils.isNotBlank(menu.getAccessAddress())) {
                vueRouter.setPath(menu.getAccessAddress());
                vueRouter.setComponent(null);
            }

           /* if (menu.getTreeLevel() == 1) {
                vueRouter.setComponent("PageView");
            }*/

            vueRouterMeta = new VueRouterMeta();
            vueRouterMeta.setIcon(menu.getIconAddress());
            vueRouterMeta.setTitle(menu.getName());
            if (StringUtils.equals(menu.getIsInternal(), PublicEnum.NO.getStatus())) {
                vueRouterMeta.setTarget("_blank");
            }
            vueRouter.setMeta(vueRouterMeta);

            resultMenus.add(vueRouter);
        }
        return TreeUtil.buildVueMenuByRecursive(resultMenus);
    }


    private void addMenuParentsByParentMenuId(List<Menu> menus, String menuId) {
        Menu parentMenu = getMenuById(menuId);
        if (null != parentMenu) {
            menus.add(parentMenu);
            addMenuParentsByParentMenuId(menus, parentMenu.getParentMenuId());
        }
    }

    private void addMenuChildsByParentMenuId(List<String> menuIds, String menuId) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Menu::getParentMenuId, menuId);
        List<Menu> menuChilds = list(queryWrapper);
        if (CollectionUtils.isNotEmpty(menuChilds)) {
            for (Menu menu : menuChilds) {
                menuIds.add(menu.getMenuId());
                addMenuChildsByParentMenuId(menuIds, menu.getMenuId());
            }
        }
    }

    private String appendCodePath(List<Menu> parentMenus, String currentMenuCode) {
        StringBuilder sb = new StringBuilder();
        if (CollectionUtils.isEmpty(parentMenus)) {
            sb.append(currentMenuCode);
            return sb.toString();
        }
        for (int i = parentMenus.size() - 1; i >= 0; i--) {
            sb.append(parentMenus.get(i).getMenuCode()).append("/");
        }
        sb.append(currentMenuCode);
        return sb.toString();
    }

    private String appendNamePath(List<Menu> parentMenus, String currentMenuName) {
        StringBuilder sb = new StringBuilder();
        if (CollectionUtils.isEmpty(parentMenus)) {
            sb.append(currentMenuName);
            return sb.toString();
        }
        for (int i = parentMenus.size() - 1; i >= 0; i--) {
            sb.append(parentMenus.get(i).getName()).append("/");
        }
        sb.append(currentMenuName);
        return sb.toString();
    }

    private String appendIdPath(List<Menu> parentMenus, String currentMenuId) {
        StringBuilder sb = new StringBuilder();
        if (CollectionUtils.isEmpty(parentMenus)) {
            sb.append(currentMenuId);
            return sb.toString();
        }
        for (int i = parentMenus.size() - 1; i >= 0; i--) {
            sb.append(parentMenus.get(i).getMenuId()).append("/");
        }
        sb.append(currentMenuId);
        return sb.toString();
    }
}
