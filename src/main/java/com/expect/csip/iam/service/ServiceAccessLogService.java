package com.expect.csip.iam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.expect.csip.iam.domain.ServiceAccessLog;
import com.expect.csip.iam.domain.condition.ServiceAccessLogCondition;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 服务访问日志 服务类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
public interface ServiceAccessLogService extends IService<ServiceAccessLog> {

    /**
     * 服务访问日志分页列表
     *
     * @param condition
     * @return
     */
    IPage<ServiceAccessLog> listServiceAccessLogPage(ServiceAccessLogCondition condition);

    /**
     * 主键查询服务访问日志
     *
     * @param logId
     * @return
     */
    ServiceAccessLog getServiceAccessLogById(String logId);

    /**
     * 主键批量查询服务访问日志
     *
     * @param logIds
     * @return
     */
    Map<String, ServiceAccessLog> listServiceAccessLogMapsByIds(List<String> logIds);

    /**
     * 主键批量删除服务访问日志
     *
     * @param logIds
     * @return
     */
    Boolean deleteServiceAccessLogByIds(List<String> logIds);

    /**
     * 主键删除服务访问日志
     *
     * @param logId
     * @return
     */
    Boolean deleteServiceAccessLogById(String logId);

    /**
     * 保存服务访问日志
     *
     * @param serviceAccessLog
     * @return
     */
    ServiceAccessLog saveServiceAccessLog(ServiceAccessLog serviceAccessLog);

    /**
     * 修改服务访问日志
     *
     * @param serviceAccessLog
     * @return
     */
    Boolean updateServiceAccessLog(ServiceAccessLog serviceAccessLog);
}
