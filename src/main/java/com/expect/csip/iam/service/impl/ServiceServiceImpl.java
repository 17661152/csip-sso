package com.expect.csip.iam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.expect.csip.iam.domain.condition.ServiceCondition;
import com.expect.csip.iam.mapper.ServiceMapper;
import com.expect.csip.iam.service.DataPermService;
import com.expect.csip.iam.service.DataPermValueService;
import com.expect.csip.iam.service.FuncPermSvcRelService;
import com.expect.csip.iam.service.MenuDataPermRelService;
import com.expect.csip.iam.service.RoleDataPermRelService;
import com.expect.csip.iam.service.ServiceService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 服务 服务实现类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@Service
public class ServiceServiceImpl extends ServiceImpl<ServiceMapper, com.expect.csip.iam.domain.Service> implements ServiceService {

    @Autowired
    private FuncPermSvcRelService funcPermSvcRelService;

    @Autowired
    private DataPermService dataPermService;

    @Autowired
    private DataPermValueService dataPermValueService;

    @Autowired
    private MenuDataPermRelService menuDataPermRelService;

    @Autowired
    private RoleDataPermRelService roleDataPermRelService;

    @Override
    public IPage<com.expect.csip.iam.domain.Service> listServicePage(ServiceCondition condition) {
        IPage<com.expect.csip.iam.domain.Service> page = condition.buildPage();
        page = baseMapper.listServicePage(page, condition);
        return page;
    }

    @Override
    public com.expect.csip.iam.domain.Service getServiceById(String serviceId) {
        return getById(serviceId);
    }

    @Override
    @Cacheable(value = "IAM_SERVICE_ACCESSADDRESS", key = "#accessAddress")
    public com.expect.csip.iam.domain.Service getServiceByAccessAddress(String accessAddress) {
        QueryWrapper<com.expect.csip.iam.domain.Service> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(com.expect.csip.iam.domain.Service::getAccessAddress, accessAddress);
        return getOne(queryWrapper);
    }

    @Override
    public Map<String, com.expect.csip.iam.domain.Service> listServiceMapsByIds(List<String> serviceIds) {
        Map<String, com.expect.csip.iam.domain.Service> resultMap = Maps.newLinkedHashMap();
        Collection<com.expect.csip.iam.domain.Service> domains = listByIds(serviceIds);
        if (CollectionUtils.isEmpty(domains)) {
            return resultMap;
        }

        for (com.expect.csip.iam.domain.Service service : domains) {
            resultMap.put(service.getServiceId(), service);
        }
        return resultMap;
    }

    @Override
    @CacheEvict(value = "IAM_SERVICE_ACCESSADDRESS", allEntries = true)
    @Transactional
    public Boolean deleteServiceByIds(List<String> serviceIds) {
        if (CollectionUtils.isEmpty(serviceIds)) {
            return Boolean.FALSE;
        }
        funcPermSvcRelService.deleteFuncPermSvcRelByServiceIds(serviceIds);
        dataPermService.deleteDataPermByServiceIds(serviceIds);
        dataPermValueService.deleteDataPermValueByServiceIds(serviceIds);
        menuDataPermRelService.deleteMenuDataPermRelByServiceIds(serviceIds);
        roleDataPermRelService.deleteRoleDataPermRelByDataPermIds(serviceIds);
        return removeByIds(serviceIds);
    }

    @Override
    @CacheEvict(value = "IAM_SERVICE_ACCESSADDRESS", allEntries = true)
    @Transactional
    public Boolean deleteServiceById(String serviceId) {
        List<String> serviceIds = Lists.newArrayList();
        serviceIds.add(serviceId);
        return deleteServiceByIds(serviceIds);
    }

    @Override
    @CacheEvict(value = "IAM_SERVICE_ACCESSADDRESS", allEntries = true)
    @Transactional
    public com.expect.csip.iam.domain.Service saveService(com.expect.csip.iam.domain.Service service) {
        save(service);
        return service;
    }

    @Override
    @CacheEvict(value = "IAM_SERVICE_ACCESSADDRESS", allEntries = true)
    @Transactional
    public Boolean updateService(com.expect.csip.iam.domain.Service service) {
        return updateById(service);
    }

    @Override
    public Boolean checkServicePerm(String userId, String accessAddress, String currentRoute) {
        Integer permNumber = baseMapper.countServicePerm(userId, accessAddress, currentRoute);
        if (permNumber > 0) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
