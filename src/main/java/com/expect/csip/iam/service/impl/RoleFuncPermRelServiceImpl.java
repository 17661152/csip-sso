package com.expect.csip.iam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.expect.common.enums.PublicEnum;
import com.expect.csip.iam.domain.RoleAuth;
import com.expect.csip.iam.domain.RoleFuncPermRel;
import com.expect.csip.iam.mapper.RoleFuncPermRelMapper;
import com.expect.csip.iam.service.RoleFuncPermRelService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>
 * 角色功能权限关系 服务实现类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@Service
public class RoleFuncPermRelServiceImpl extends ServiceImpl<RoleFuncPermRelMapper, RoleFuncPermRel> implements RoleFuncPermRelService {


    @Override
    @Transactional
    public Boolean saveRoleFuncPermRel(RoleAuth roleAuth) {
        List<RoleFuncPermRel> roleFuncPermRels = Lists.newArrayList();

        QueryWrapper<RoleFuncPermRel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleFuncPermRel::getRoleId, roleAuth.getRoleId());
        remove(queryWrapper);

        List<String> funcPermIds = roleAuth.getFuncPermIds();
        List<String> halfFuncPermIds = roleAuth.getHalfFuncPermIds();

        funcPermIds.forEach(funcPermId -> {
            RoleFuncPermRel roleFuncPermRel = null;
            if (StringUtils.isNotBlank(funcPermId)) {
                roleFuncPermRel = new RoleFuncPermRel();
                roleFuncPermRel.setFuncPermId(funcPermId);
                roleFuncPermRel.setRoleId(roleAuth.getRoleId());
                roleFuncPermRel.setHalfChecked(PublicEnum.NO.getStatus());
                roleFuncPermRels.add(roleFuncPermRel);
            }
        });

        halfFuncPermIds.forEach(funcPermId -> {
            RoleFuncPermRel roleFuncPermRel = null;
            if (StringUtils.isNotBlank(funcPermId)) {
                roleFuncPermRel = new RoleFuncPermRel();
                roleFuncPermRel.setFuncPermId(funcPermId);
                roleFuncPermRel.setRoleId(roleAuth.getRoleId());
                roleFuncPermRel.setHalfChecked(PublicEnum.YES.getStatus());
                roleFuncPermRels.add(roleFuncPermRel);
            }
        });
        return saveBatch(roleFuncPermRels);
    }

    @Override
    @Transactional
    public Boolean deleteRoleFuncPermRelByFuncPermIds(List<String> funcPermIds) {
        QueryWrapper<RoleFuncPermRel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(RoleFuncPermRel::getFuncPermId, funcPermIds);
        return remove(queryWrapper);
    }

    @Override
    public RoleAuth getRoleFuncPermIds(String roleId) {
        RoleAuth roleAuth = new RoleAuth();

        List<String> funcPermIds = Lists.newArrayList();
        List<String> halfFuncPermIds = Lists.newArrayList();
        RoleFuncPermRel roleFuncPermRel = new RoleFuncPermRel();
        roleFuncPermRel.setRoleId(roleId);
        roleFuncPermRel.setHalfChecked(PublicEnum.NO.getStatus());
        List<RoleFuncPermRel> checkedKeys = baseMapper.listRoleFuncPerms(roleFuncPermRel);
        checkedKeys.forEach(checkedKey -> {
            if (null != checkedKey) {
                funcPermIds.add(checkedKey.getFuncPermId());
            }
        });

        roleFuncPermRel.setHalfChecked(PublicEnum.YES.getStatus());
        List<RoleFuncPermRel> halfCheckedKeys = baseMapper.listRoleFuncPerms(roleFuncPermRel);
        halfCheckedKeys.forEach(halfCheckedKey -> {
            if (null != halfCheckedKey) {
                halfFuncPermIds.add(halfCheckedKey.getFuncPermId());
            }
        });
        roleAuth.setFuncPermIds(funcPermIds);
        roleAuth.setHalfFuncPermIds(halfFuncPermIds);
        return roleAuth;
    }
}
