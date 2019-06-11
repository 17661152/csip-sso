package com.expect.csip.iam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.expect.csip.iam.domain.RoleDataPermRel;
import com.expect.csip.iam.domain.DataPermValue;
import com.expect.csip.iam.mapper.RoleDataPermRelMapper;
import com.expect.csip.iam.service.DataPermValueService;
import com.expect.csip.iam.service.RoleDataPermRelService;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>
 * 角色数据权限关系 服务实现类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@Service
public class RoleDataPermRelServiceImpl extends ServiceImpl<RoleDataPermRelMapper, RoleDataPermRel> implements RoleDataPermRelService {

    @Autowired
    private DataPermValueService dataPermValueService;

    @Override
    public List<RoleDataPermRel> listUserDataPerms(String userId, String accessAddress) {
        return baseMapper.listUserDataPerms(userId, accessAddress);
    }

    @Override
    @Transactional
    public Boolean deleteRoleDataPermRelByDataPermIds(List<String> dataPermIds) {
        QueryWrapper<RoleDataPermRel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(RoleDataPermRel::getDataPermId, dataPermIds);
        return remove(queryWrapper);
    }

    @Override
    @Transactional
    public Boolean updateRoleDataPermRel(String dataPermId, List<String> dataPermValueIds) {
        QueryWrapper<RoleDataPermRel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleDataPermRel::getDataPermId, dataPermId);
        if (CollectionUtils.isNotEmpty(dataPermValueIds)) {
            queryWrapper.lambda().notIn(RoleDataPermRel::getDataPermValueId, dataPermValueIds);
        }
        return remove(queryWrapper);
    }

    @Override
    @Transactional
    public Boolean saveRoleDataPermRel(String roleId, List<String> dataPermValueIds) {
        QueryWrapper<RoleDataPermRel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleDataPermRel::getRoleId, roleId);
        remove(queryWrapper);

        List<RoleDataPermRel> roleDataPermRels = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(dataPermValueIds)) {
            dataPermValueIds.forEach(dataPermValueId -> {
                DataPermValue dataPermValue = dataPermValueService.getById(dataPermValueId);
                RoleDataPermRel roleDataPermRel = new RoleDataPermRel();
                roleDataPermRel.setRoleId(roleId);
                roleDataPermRel.setDataPermId(dataPermValue.getDataPermId());
                roleDataPermRel.setDataPermValueId(dataPermValueId);
                roleDataPermRels.add(roleDataPermRel);
            });
        }
        return saveBatch(roleDataPermRels);
    }

    @Override
    public List<String> listRoleDataPermValueIds(String roleId) {
        List<String> dataPermValueIds = Lists.newArrayList();
        List<RoleDataPermRel> roleDataPermRels = baseMapper.listRoleDataPermRelByRoleId(roleId);
        if (CollectionUtils.isNotEmpty(roleDataPermRels)) {
            roleDataPermRels.forEach(roleDataPermRel -> {
                dataPermValueIds.add(roleDataPermRel.getDataPermValueId());
            });
        }
        return dataPermValueIds;
    }
}
