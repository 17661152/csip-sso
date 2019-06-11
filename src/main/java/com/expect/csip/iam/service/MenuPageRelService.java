package com.expect.csip.iam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.expect.csip.iam.domain.MenuPageRel;

import java.util.List;


/**
 * <p>
 * 菜单页面关系 服务类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
public interface MenuPageRelService extends IService<MenuPageRel> {


    /**
     * 保存菜单页面关系
     *
     * @param pageId
     * @param menuIds
     * @return
     */
    Boolean saveMenuPageRel(String pageId, List<String> menuIds);

    /**
     * 根据页面ID集合删除菜单与菜单的关系
     *
     * @param pageIds
     * @return
     */
    Boolean deleteMenuPageRelByPageIds(List<String> pageIds);

    /**
     * 根据菜单ID集合删除菜单与页面的关系
     *
     * @param menuIds
     * @return
     */
    Boolean deleteMenuPageRelByMenuIds(List<String> menuIds);

    /**
     * 查询出页面关联的菜单ID集合
     *
     * @param pageId
     * @return
     */
    List<String> listMenuPageMenuIds(String pageId);

    /**
     * 统计菜单下页面的数量
     *
     * @param menuId
     * @return
     */
    Integer countMenuPageRelByMenuId(String menuId);
}
