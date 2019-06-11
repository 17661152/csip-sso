package com.expect.csip.iam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.expect.csip.iam.domain.condition.DataPermValueCondition;
import com.expect.csip.iam.domain.DataPermValue;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 数据权限值 Mapper 接口
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
public interface DataPermValueMapper extends BaseMapper<DataPermValue> {

    /**
     * 权限值分页列表
     *
     * @param page
     * @param condition
     * @return
     */
    IPage<DataPermValue> listDataPermValuePage(IPage<DataPermValue> page, @Param("condition") DataPermValueCondition condition);
}
