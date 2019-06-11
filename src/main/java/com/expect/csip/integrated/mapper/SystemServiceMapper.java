package com.expect.csip.integrated.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.expect.csip.integrated.domain.SystemService;
import com.expect.csip.integrated.domain.condition.SystemServiceCondition;
import org.apache.ibatis.annotations.Param;

/**
 * 集成系统服务表(SystemService)数据库访问层
 *
 * @author jack.zeng
 * @since 2019-05-30 14:06:44
 */
public interface SystemServiceMapper extends BaseMapper<SystemService> {

    /**
     * 系统服务分页列表
     *
     * @param page
     * @param condition
     * @return
     */
    IPage<SystemService> listSystemServicePage(IPage<SystemService> page, @Param("condition") SystemServiceCondition condition);
}