package com.expect.csip.iam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.expect.csip.iam.domain.MenuPageRel;
import com.expect.csip.iam.mapper.MenuPageRelMapper;
import com.expect.csip.iam.service.MenuPageRelService;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>
 * 菜单页面关系 服务实现类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@Service
public class MenuPageRelServiceImpl extends ServiceImpl<MenuPageRelMapper, MenuPageRel> implements MenuPageRelService {


    @Override
    @Transactional
    public Boolean saveMenuPageRel(String pageId, List<String> menuIds) {
        List<String> pageIds = Lists.newArrayList();
        pageIds.add(pageId);
        deleteMenuPageRelByPageIds(pageIds);

        if (CollectionUtils.isEmpty(menuIds)) {
            return Boolean.TRUE;
        }

        List<MenuPageRel> menuPageRels = Lists.newArrayList();
        menuIds.forEach(menuId -> {
            MenuPageRel menuPageRel = null;
            if (StringUtils.isNotBlank(menuId)) {
                menuPageRel = new MenuPageRel();
                menuPageRel.setMenuId(menuId);
                menuPageRel.setPageId(pageId);
                menuPageRels.add(menuPageRel);
            }
        });
        return saveBatch(menuPageRels);
    }

    @Override
    @Transactional
    public Boolean deleteMenuPageRelByPageIds(List<String> pageIds) {
        QueryWrapper<MenuPageRel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(MenuPageRel::getPageId, pageIds);
        return remove(queryWrapper);
    }

    @Override
    public Boolean deleteMenuPageRelByMenuIds(List<String> menuIds) {
        QueryWrapper<MenuPageRel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(MenuPageRel::getMenuId, menuIds);
        return remove(queryWrapper);
    }

    @Override
    public List<String> listMenuPageMenuIds(String pageId) {
        List<String> menuIds = Lists.newArrayList();

        QueryWrapper<MenuPageRel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(MenuPageRel::getPageId, pageId);
        List<MenuPageRel> menuPageRels = list(queryWrapper);
        menuPageRels.forEach(menuPageRel -> {
            if (null != menuPageRel) {
                menuIds.add(menuPageRel.getMenuId());
            }
        });
        return menuIds;
    }

    @Override
    public Integer countMenuPageRelByMenuId(String menuId) {
        QueryWrapper<MenuPageRel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(MenuPageRel::getMenuId, menuId);
        return count(queryWrapper);
    }
}
