package com.expect.csip.iam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.expect.csip.iam.domain.Organ;
import com.expect.csip.iam.domain.condition.OrganCondition;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 组织机构 Mapper 接口
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
public interface OrganMapper extends BaseMapper<Organ> {

    /**
     * 组织分页列表
     *
     * @param page
     * @param condition
     * @return
     */
    IPage<Organ> listOrganPage(IPage<Organ> page, @Param("condition") OrganCondition condition);
}
