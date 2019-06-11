package com.expect.csip.iam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.expect.csip.iam.domain.DataPerm;
import com.expect.csip.iam.domain.MenuDataPermRel;
import com.expect.csip.iam.domain.DataPermValue;
import com.expect.csip.iam.mapper.MenuDataPermRelMapper;
import com.expect.csip.iam.service.MenuDataPermRelService;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>
 * 菜单数据权限关系 服务实现类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@Service
public class MenuDataPermRelServiceImpl extends ServiceImpl<MenuDataPermRelMapper, MenuDataPermRel> implements MenuDataPermRelService {


    @Override
    @Transactional
    public Boolean deleteMenuDataPermRelByServiceIds(List<String> serviceIds) {
        QueryWrapper<MenuDataPermRel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(MenuDataPermRel::getDataPermId, serviceIds);
        return remove(queryWrapper);
    }

    @Override
    @Transactional
    public Boolean deleteMenuDataPermRelByMenuIds(List<String> menuIds) {
        QueryWrapper<MenuDataPermRel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(MenuDataPermRel::getMenuId, menuIds);
        return remove(queryWrapper);
    }

    @Override
    @Transactional
    public Boolean saveMenuDataPermRel(String menuId, List<DataPerm> dataPerms) {
        List<String> menuIds = Lists.newArrayList();
        menuIds.add(menuId);
        deleteMenuDataPermRelByMenuIds(menuIds);

        List<MenuDataPermRel> menuDataPermRels = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(dataPerms)) {
            dataPerms.forEach(dataPerm -> {
                MenuDataPermRel menuDataPermRel = new MenuDataPermRel();
                menuDataPermRel.setDataPermId(dataPerm.getDataPermId());
                menuDataPermRel.setMenuId(menuId);
                menuDataPermRels.add(menuDataPermRel);
            });
        }
        return saveBatch(menuDataPermRels);
    }

    @Override
    public List<DataPerm> listMenuDataPerms(String menuId) {
        return baseMapper.listMenuDataPerms(menuId);
    }

    @Override
    public List<DataPermValue> listMenuDataPermValues(String menuId) {
        return baseMapper.listMenuDataPermValues(menuId);
    }
}
