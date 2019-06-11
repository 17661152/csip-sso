package com.expect.csip.iam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.expect.csip.iam.domain.Service;
import com.expect.csip.iam.domain.condition.ServiceCondition;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 服务 Mapper 接口
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
public interface ServiceMapper extends BaseMapper<Service> {

    /**
     * 统计当前用户访问服务权限数量
     *
     * @param userId
     * @param accessAddress
     * @param currentRoute
     * @return
     */
    Integer countServicePerm(@Param("userId") String userId, @Param("accessAddress") String accessAddress, @Param("currentRoute") String currentRoute);

    /**
     * 服务分页列表
     *
     * @param page
     * @param condition
     * @return
     */
    IPage<Service> listServicePage(IPage<Service> page, @Param("condition") ServiceCondition condition);
}
