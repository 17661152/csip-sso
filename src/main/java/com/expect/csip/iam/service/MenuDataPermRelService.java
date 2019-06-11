package com.expect.csip.iam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.expect.csip.iam.domain.DataPerm;
import com.expect.csip.iam.domain.MenuDataPermRel;
import com.expect.csip.iam.domain.DataPermValue;

import java.util.List;


/**
 * <p>
 * 菜单数据权限关系 服务类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
public interface MenuDataPermRelService extends IService<MenuDataPermRel> {


    /**
     * 删除服务下菜单与数据权限关系
     *
     * @param serviceIds
     * @return
     */
    Boolean deleteMenuDataPermRelByServiceIds(List<String> serviceIds);

    /**
     * 保存菜单下的数据权限
     *
     * @param menuId
     * @param dataPerms
     * @return
     */
    Boolean saveMenuDataPermRel(String menuId, List<DataPerm> dataPerms);

    /**
     * 删除菜单的数据权限关系
     *
     * @param menuIds
     * @return
     */
    Boolean deleteMenuDataPermRelByMenuIds(List<String> menuIds);

    /**
     * 查询菜单下的数据权限列表
     *
     * @param menuId
     * @return
     */
    List<DataPerm> listMenuDataPerms(String menuId);

    /**
     * 查询菜单下的数据权限值列表
     *
     * @param menuId
     * @return
     */
    List<DataPermValue> listMenuDataPermValues(String menuId);
}
