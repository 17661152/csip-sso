package com.expect.csip.iam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.expect.csip.iam.domain.Element;
import com.expect.csip.iam.domain.condition.ElementCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 元素 Mapper 接口
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
public interface ElementMapper extends BaseMapper<Element> {

    /**
     * 元素分页列表
     *
     * @param page
     * @param condition
     * @return
     */
    IPage<Element> listElementPage(IPage<Element> page, @Param("condition") ElementCondition condition);

    /**
     * 权限元素列表
     *
     * @param userId
     * @return
     */
    List<Element> listAuthElement(@Param("userId") String userId);
}
