package com.expect.csip.integrated.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.expect.csip.integrated.domain.SystemService;
import com.expect.csip.integrated.domain.condition.SystemServiceCondition;
import com.expect.csip.integrated.mapper.SystemServiceMapper;
import com.expect.csip.integrated.service.SystemServiceService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



/**
 * 集成系统服务表(SystemService)服务实现类
 *
 * @author jack.zeng
 * @since 2019-05-30 14:06:44
 */
@Service("systemServiceService")
public class SystemServiceServiceImpl extends ServiceImpl<SystemServiceMapper, SystemService> implements SystemServiceService {


    @Override
    public IPage<SystemService> listSystemServicePage(SystemServiceCondition condition) {
        IPage<SystemService> page = condition.buildPage();
        page = baseMapper.listSystemServicePage(page,condition );
        return page;
    }


    @Override
    public SystemService getSystemServiceById(String systemId) {
        return getById(systemId);
    }


    @Override
    public Map<String, SystemService> listSystemServiceMapsByIds(List<String> systemIds) {
        Map<String, SystemService> resultMap = new LinkedHashMap<>();
        Collection<SystemService> domains = listByIds(systemIds);
        if (CollectionUtils.isEmpty(domains)) {
            return resultMap;
        }

        for (SystemService systemService : domains) {
            resultMap.put(systemService.getSystemId(), systemService);
        }
        return resultMap;
    }


    @Override
    @Transactional
    public Boolean deleteSystemServiceByIds(List<String> systemIds) {
        return removeByIds(systemIds);
    }


    @Override
    @Transactional
    public Boolean deleteSystemServiceById(String systemId) {
        return removeById(systemId);
    }


    @Override
    @Transactional
    public SystemService saveSystemService(SystemService systemService) {
        save(systemService);
        return systemService;
    }


    @Override
    @Transactional
    public Boolean updateSystemService(SystemService systemService) {
        return updateById(systemService);
    }
}