package com.expect.csip.iam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.expect.csip.iam.domain.Page;
import com.expect.csip.iam.domain.condition.PageCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 页面 Mapper 接口
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
public interface PageMapper extends BaseMapper<Page> {

    /**
     * 页面分页列表
     * @param page
     * @param condition
     * @return
     */
    IPage<Page> listPagePage(IPage<Page> page, @Param("condition") PageCondition condition);

    /**
     * 查询菜单关联的页面列表
     * @param menuIds
     * @return
     */
    List<Page> listMenuPage(@Param("menuIds") List<String> menuIds);
}
