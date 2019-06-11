package com.expect.csip.iam.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.expect.common.enums.FuncEnum;
import com.expect.common.enums.PublicEnum;
import com.expect.csip.iam.domain.FuncPerm;
import com.expect.csip.iam.domain.Menu;
import com.expect.csip.iam.domain.Page;
import com.expect.csip.iam.domain.condition.PageCondition;
import com.expect.csip.iam.mapper.PageMapper;
import com.expect.csip.iam.service.FuncPermService;
import com.expect.csip.iam.service.FuncPermSvcRelService;
import com.expect.csip.iam.service.MenuPageRelService;
import com.expect.csip.iam.service.MenuService;
import com.expect.csip.iam.service.PageService;
import com.expect.csip.iam.service.RoleFuncPermRelService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 页面 服务实现类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@Service
public class PageServiceImpl extends ServiceImpl<PageMapper, Page> implements PageService {

    @Autowired
    private FuncPermService funcPermService;

    @Autowired
    private MenuPageRelService menuPageRelService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private FuncPermSvcRelService funcPermSvcRelService;

    @Autowired
    private RoleFuncPermRelService roleFuncPermRelService;

    @Override
    public IPage<Page> listPagePage(PageCondition condition) {
        IPage<Page> page = condition.buildPage();
        return baseMapper.listPagePage(page, condition);
    }

    @Override
    public Page getPageById(String pageId) {
        Page page = getById(pageId);
        if (null != page) {
            FuncPerm funcPerm = funcPermService.getFuncPermById(pageId);
            page.setName(funcPerm.getName());
            page.setFuncPermId(funcPerm.getFuncPermId());
            page.setIsEnable(funcPerm.getIsEnable());
            page.setType(funcPerm.getType());
            page.setSortNumber(funcPerm.getSortNumber());
            page.setDescr(funcPerm.getDescr());

            page.setMenuIds(menuPageRelService.listMenuPageMenuIds(pageId));

            List<com.expect.csip.iam.domain.Service> services = funcPermSvcRelService.listFuncPermService(page.getPageId());
            page.setServices(services);
        }
        return page;
    }

    @Override
    public Map<String, Page> listPageMapsByIds(List<String> pageIds) {
        Map<String, Page> resultMap = Maps.newLinkedHashMap();
        Collection<Page> domains = listByIds(pageIds);
        if (CollectionUtils.isEmpty(domains)) {
            return resultMap;
        }

        for (Page page : domains) {
            resultMap.put(page.getPageId(), page);
        }
        return resultMap;
    }

    @Override
    @Transactional
    public Boolean deletePageByIds(List<String> pageIds) {
        return removeByIds(pageIds);
    }

    @Override
    @Transactional
    public Boolean deletePageById(String pageId) {
        funcPermService.deleteFuncPermById(pageId);
        List<String> permIds = Lists.newArrayList();
        permIds.add(pageId);
        menuPageRelService.deleteMenuPageRelByPageIds(permIds);
        funcPermSvcRelService.deleteFuncPermSvcRelByFuncPermIds(permIds);
        roleFuncPermRelService.deleteRoleFuncPermRelByFuncPermIds(permIds);
        return removeById(pageId);
    }

    @Override
    @Transactional
    public Page savePage(Page page) {
        //先保存功能权限
        FuncPerm funcPerm = BeanUtil.toBean(page, FuncPerm.class);
        funcPerm.setType(FuncEnum.PAGE.getType());
        funcPerm.setIsEnable(PublicEnum.ACTIVE.getStatus());
        funcPerm = funcPermService.saveFuncPerm(funcPerm);

        //再保存菜单
        page.setPageId(funcPerm.getFuncPermId());
        save(page);

        menuPageRelService.saveMenuPageRel(page.getPageId(), page.getMenuIds());

        String menuId = page.getMenuIds().get(0);
        Menu menu = new Menu();
        menu.setMenuId(menuId);
        menu.setPageId(page.getPageId());
        menuService.updateById(menu);

        funcPermSvcRelService.saveFuncPermSvcRel(page.getPageId(), page.getServices());
        return page;
    }

    @Override
    @Transactional
    public Boolean updatePage(Page page) {
        FuncPerm funcPerm = BeanUtil.toBean(page, FuncPerm.class);
        funcPerm.setFuncPermId(page.getPageId());
        funcPerm.setType(FuncEnum.PAGE.getType());
        funcPermService.updateFuncPerm(funcPerm);

        menuPageRelService.saveMenuPageRel(page.getPageId(), page.getMenuIds());

        String menuId = page.getMenuIds().get(0);
        Menu menu = new Menu();
        menu.setMenuId(menuId);
        menu.setPageId(page.getPageId());
        menuService.updateById(menu);

        funcPermSvcRelService.saveFuncPermSvcRel(page.getPageId(), page.getServices());
        return updateById(page);
    }

    @Override
    public List<Page> listMenuPage(List<String> menuIds) {
        return baseMapper.listMenuPage(menuIds);
    }

    @Override
    @Transactional
    public Boolean disablePage(String pageId) {
        List<String> pageIds = Lists.newArrayList();
        pageIds.add(pageId);
        return funcPermService.disableFuncPerm(pageIds);
    }

    @Override
    @Transactional
    public Boolean enablePage(String pageId) {
        List<String> pageIds = Lists.newArrayList();
        pageIds.add(pageId);
        return funcPermService.enableFuncPerm(pageIds);
    }
}
