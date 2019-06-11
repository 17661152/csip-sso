package com.expect.csip.iam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.expect.csip.iam.domain.FuncPermSvcRel;
import com.expect.csip.iam.mapper.FuncPermSvcRelMapper;
import com.expect.csip.iam.service.FuncPermSvcRelService;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>
 * 功能权限服务关系 服务实现类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@Service
public class FuncPermSvcRelServiceImpl extends ServiceImpl<FuncPermSvcRelMapper, FuncPermSvcRel> implements FuncPermSvcRelService {


    @Override
    @Transactional
    public Boolean deleteFuncPermSvcRelByServiceIds(List<String> serviceIds) {
        QueryWrapper<FuncPermSvcRel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(FuncPermSvcRel::getServiceId, serviceIds);
        return remove(queryWrapper);
    }

    @Override
    @Transactional
    public Boolean deleteFuncPermSvcRelByFuncPermIds(List<String> funcPermIds) {
        QueryWrapper<FuncPermSvcRel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(FuncPermSvcRel::getFuncPermId, funcPermIds);
        return remove(queryWrapper);
    }

    @Override
    @Transactional
    public Boolean saveFuncPermSvcRel(String funcId, List<com.expect.csip.iam.domain.Service> services) {
        List<String> permIds = Lists.newArrayList();
        permIds.add(funcId);
        deleteFuncPermSvcRelByFuncPermIds(permIds);

        List<FuncPermSvcRel> funcPermSvcRels = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(services)) {
            services.forEach(service -> {
                FuncPermSvcRel funcPermSvcRel = new FuncPermSvcRel();
                funcPermSvcRel.setFuncPermId(funcId);
                funcPermSvcRel.setServiceId(service.getServiceId());
                funcPermSvcRels.add(funcPermSvcRel);
            });
        }
        return saveBatch(funcPermSvcRels);
    }

    @Override
    public List<com.expect.csip.iam.domain.Service> listFuncPermService(String funcPermId) {
        return baseMapper.listFuncPermService(funcPermId);
    }
}
