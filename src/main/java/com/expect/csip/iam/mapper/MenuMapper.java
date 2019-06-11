package com.expect.csip.iam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.expect.common.domain.TreeNode;
import com.expect.csip.iam.domain.Menu;
import com.expect.csip.iam.domain.condition.MenuCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单 Mapper 接口
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 查询我的菜单
     *
     * @param userId
     * @return
     */
    List<Menu> listAuthVueRouterMenu(@Param("userId") String userId);

    /**
     * 查询所有菜单
     *
     * @param condition
     * @return
     */
    List<Menu> listMenu(MenuCondition condition);

    /**
     * 查询所有菜单、页面、元素列表
     *
     * @return
     */
    List<TreeNode> listFuncPerms();

    /**
     * 查询一、二级菜单
     *
     * @return
     */
    List<Menu> listOneOrTwoMenu();

    /**
     * 菜单分页列表
     *
     * @param condition
     * @return
     */
    IPage<Menu> listMenuPage(IPage<Menu> page, @Param("condition") MenuCondition condition);
}
