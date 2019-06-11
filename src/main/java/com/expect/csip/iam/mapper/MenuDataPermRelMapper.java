package com.expect.csip.iam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.expect.csip.iam.domain.DataPerm;
import com.expect.csip.iam.domain.MenuDataPermRel;
import com.expect.csip.iam.domain.DataPermValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单数据权限关系 Mapper 接口
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
public interface MenuDataPermRelMapper extends BaseMapper<MenuDataPermRel> {

    /**
     * 查询菜单下的数据权限列表
     *
     * @param menuId
     * @return
     */
    List<DataPerm> listMenuDataPerms(@Param("menuId") String menuId);

    /**
     * 查询菜单下的数据权限值列表
     *
     * @param menuId
     * @return
     */
    List<DataPermValue> listMenuDataPermValues(@Param("menuId") String menuId);
}
