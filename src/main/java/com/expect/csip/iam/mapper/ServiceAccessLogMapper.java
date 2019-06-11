package com.expect.csip.iam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.expect.csip.iam.domain.ServiceAccessLog;
import com.expect.csip.iam.domain.condition.ServiceAccessLogCondition;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 服务访问日志 Mapper 接口
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
public interface ServiceAccessLogMapper extends BaseMapper<ServiceAccessLog> {

    /**
     * 日志分页列表
     *
     * @param condition
     * @return
     */
    IPage<ServiceAccessLog> listServiceAccessLogPage(IPage<ServiceAccessLog> page, @Param("condition") ServiceAccessLogCondition condition);
}
