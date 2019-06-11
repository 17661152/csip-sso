package com.expect.csip.iam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.expect.common.enums.PublicEnum;
import com.expect.csip.iam.domain.FuncPerm;
import com.expect.csip.iam.domain.condition.FuncPermCondition;
import com.expect.csip.iam.mapper.FuncPermMapper;
import com.expect.csip.iam.service.FuncPermService;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 功能权限 服务实现类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@Service
public class FuncPermServiceImpl extends ServiceImpl<FuncPermMapper, FuncPerm> implements FuncPermService {


    @Override
    public IPage<FuncPerm> listFuncPermPage(FuncPermCondition condition) {
        IPage<FuncPerm> page = condition.buildPage();
        QueryWrapper<FuncPerm> queryWrapper = condition.buildQueryWrapper(FuncPerm.class);
        return page(page, queryWrapper);
    }

    @Override
    public FuncPerm getFuncPermById(String funcPermId) {
        return getById(funcPermId);
    }

    @Override
    public Map<String, FuncPerm> listFuncPermMapsByIds(List<String> funcPermIds) {
        Map<String, FuncPerm> resultMap = Maps.newLinkedHashMap();
        Collection<FuncPerm> domains = listByIds(funcPermIds);
        if (CollectionUtils.isEmpty(domains)) {
            return resultMap;
        }

        for (FuncPerm funcPerm : domains) {
            resultMap.put(funcPerm.getFuncPermId(), funcPerm);
        }
        return resultMap;
    }

    @Override
    @Transactional
    public Boolean deleteFuncPermByIds(List<String> funcPermIds) {
        return removeByIds(funcPermIds);
    }

    @Override
    @Transactional
    public Boolean deleteFuncPermById(String funcPermId) {
        return removeById(funcPermId);
    }

    @Override
    @Transactional
    public FuncPerm saveFuncPerm(FuncPerm funcPerm) {
        save(funcPerm);
        return funcPerm;
    }

    @Override
    @Transactional
    public Boolean updateFuncPerm(FuncPerm funcPerm) {
        return updateById(funcPerm);
    }

    @Override
    @Transactional
    public Boolean disableFuncPerm(List<String> funcPermIds) {
        UpdateWrapper<FuncPerm> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().set(FuncPerm::getIsEnable, PublicEnum.UNACTIVE.getStatus())
                .in(FuncPerm::getFuncPermId, funcPermIds);
        return update(updateWrapper);
    }

    @Override
    @Transactional
    public Boolean enableFuncPerm(List<String> funcPermIds) {
        UpdateWrapper<FuncPerm> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().set(FuncPerm::getIsEnable, PublicEnum.ACTIVE.getStatus())
                .in(FuncPerm::getFuncPermId, funcPermIds);
        return update(updateWrapper);
    }
}
