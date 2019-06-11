package com.expect.csip.iam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.expect.csip.iam.domain.DataPerm;
import com.expect.csip.iam.domain.condition.DataPermCondition;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 数据权限 Mapper 接口
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
public interface DataPermMapper extends BaseMapper<DataPerm> {
    /**
     * 数据权限分页列表
     *
     * @param page
     * @param condition
     * @return
     */
    IPage<DataPerm> listDataPermPage(IPage<DataPerm> page, @Param("condition") DataPermCondition condition);
}
