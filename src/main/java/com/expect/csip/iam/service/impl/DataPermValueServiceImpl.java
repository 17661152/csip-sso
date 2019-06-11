package com.expect.csip.iam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.expect.csip.iam.domain.condition.DataPermValueCondition;
import com.expect.csip.iam.domain.DataPermValue;
import com.expect.csip.iam.mapper.DataPermValueMapper;
import com.expect.csip.iam.service.DataPermValueService;
import com.expect.csip.iam.service.RoleDataPermRelService;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>
 * 数据权限值 服务实现类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@Service
public class DataPermValueServiceImpl extends ServiceImpl<DataPermValueMapper, DataPermValue> implements DataPermValueService {

    @Autowired
    private RoleDataPermRelService roleDataPermRelService;

    @Override
    public IPage<DataPermValue> listDataPermValuePage(DataPermValueCondition condition) {
        IPage<DataPermValue> page = condition.buildPage();
        return baseMapper.listDataPermValuePage(page, condition);
    }

    @Override
    @Transactional
    public Boolean deleteDataPermValueByServiceIds(List<String> serviceIds) {
        QueryWrapper<DataPermValue> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(DataPermValue::getDataPermId, serviceIds);
        return remove(queryWrapper);
    }

    @Override
    @Transactional
    public Boolean updateDataPermValue(String dataPermId, List<DataPermValue> dataPermValues) {
        QueryWrapper<DataPermValue> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(DataPermValue::getDataPermId, dataPermId);
        List<String> dataPermValueIds = Lists.newArrayList();
        List<DataPermValue> saveDataPermValues = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(dataPermValues)) {
            List<DataPermValue> updateDataPermValues = Lists.newArrayList();
            dataPermValues.forEach(dataPermValue -> {
                if (StringUtils.isBlank(dataPermValue.getDataPermValueId())) {
                    saveDataPermValues.add(dataPermValue);
                } else {
                    updateDataPermValues.add(dataPermValue);
                    dataPermValueIds.add(dataPermValue.getDataPermValueId());
                }
            });
            if (CollectionUtils.isNotEmpty(dataPermValueIds)) {
                queryWrapper.lambda().notIn(DataPermValue::getDataPermValueId, dataPermValueIds);
            }
            if (CollectionUtils.isNotEmpty(updateDataPermValues)) {
                updateBatchById(updateDataPermValues);
            }
        }
        remove(queryWrapper);
        roleDataPermRelService.updateRoleDataPermRel(dataPermId, dataPermValueIds);
        return saveBatch(saveDataPermValues);
    }

    @Override
    public List<DataPermValue> listDataPermValue(DataPermValueCondition condition) {
        QueryWrapper<DataPermValue> queryWrapper = condition.buildQueryWrapper(DataPermValue.class);
        return list(queryWrapper);
    }
}
