package com.expect.csip.iam.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.expect.ip2region.IP2regionTemplate;
import com.expect.ip2region.ext.RegionAddress;
import com.expect.csip.iam.domain.ServiceAccessLog;
import com.expect.csip.iam.domain.User;
import com.expect.csip.iam.domain.condition.ServiceAccessLogCondition;
import com.expect.csip.iam.mapper.ServiceAccessLogMapper;
import com.expect.csip.iam.service.ServiceAccessLogService;
import com.expect.csip.iam.service.UserService;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 服务访问日志 服务实现类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@Service
public class ServiceAccessLogServiceImpl extends ServiceImpl<ServiceAccessLogMapper, ServiceAccessLog> implements ServiceAccessLogService {

    @Autowired
    private UserService userService;

    @Autowired
    private IP2regionTemplate ip2regionTemplate;

    @Override
    public IPage<ServiceAccessLog> listServiceAccessLogPage(ServiceAccessLogCondition condition) {
        IPage<ServiceAccessLog> page = condition.buildPage();
        return baseMapper.listServiceAccessLogPage(page, condition);
    }

    @Override
    public ServiceAccessLog getServiceAccessLogById(String logId) {
        ServiceAccessLog serviceAccessLog = getById(logId);
        if (null != serviceAccessLog) {
            User user = userService.getById(serviceAccessLog.getUserId());
            if (null != user) {
                serviceAccessLog.setLoginName(user.getLoginName());
                serviceAccessLog.setUserName(user.getName());
            }
            if (StringUtils.isNotBlank(serviceAccessLog.getSourceIp())) {
                try {
                    RegionAddress regionAddress = ip2regionTemplate.getRegionAddress(serviceAccessLog.getSourceIp());
                    serviceAccessLog.setCountry(regionAddress.getCountry());
                    serviceAccessLog.setProvince(regionAddress.getProvince());
                    serviceAccessLog.setCity(regionAddress.getCity());
                    serviceAccessLog.setIsp(regionAddress.getISP());
                } catch (Throwable e) {
                    throw new ApiException(e);
                }
            }
        }
        return serviceAccessLog;
    }

    @Override
    public Map<String, ServiceAccessLog> listServiceAccessLogMapsByIds(List<String> logIds) {
        Map<String, ServiceAccessLog> resultMap = Maps.newLinkedHashMap();
        Collection<ServiceAccessLog> domains = listByIds(logIds);
        if (CollectionUtils.isEmpty(domains)) {
            return resultMap;
        }

        for (ServiceAccessLog serviceAccessLog : domains) {
            resultMap.put(serviceAccessLog.getLogId(), serviceAccessLog);
        }
        return resultMap;
    }

    @Override
    @Transactional
    public Boolean deleteServiceAccessLogByIds(List<String> logIds) {
        return removeByIds(logIds);
    }

    @Override
    @Transactional
    public Boolean deleteServiceAccessLogById(String logId) {
        return removeById(logId);
    }

    @Override
    @Async
    @Transactional
    public ServiceAccessLog saveServiceAccessLog(ServiceAccessLog serviceAccessLog) {
        save(serviceAccessLog);
        return serviceAccessLog;
    }

    @Override
    @Transactional
    public Boolean updateServiceAccessLog(ServiceAccessLog serviceAccessLog) {
        return updateById(serviceAccessLog);
    }
}
