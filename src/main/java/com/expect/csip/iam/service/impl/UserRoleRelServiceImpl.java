package com.expect.csip.iam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.expect.csip.iam.domain.UserRoleRel;
import com.expect.csip.iam.mapper.UserRoleRelMapper;
import com.expect.csip.iam.service.UserRoleRelService;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户角色关系 服务实现类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@Service
public class UserRoleRelServiceImpl extends ServiceImpl<UserRoleRelMapper, UserRoleRel> implements UserRoleRelService {


    @Override
    @Transactional
    public Boolean saveUserRoleRel(String userId, List<String> roleIds) {
        QueryWrapper<UserRoleRel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserRoleRel::getUserId, userId);
        remove(queryWrapper);
        if (CollectionUtils.isEmpty(roleIds)) {
            return Boolean.TRUE;
        }
        List<UserRoleRel> userRoleRels = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(roleIds)) {
            roleIds.forEach(roleId -> {
                UserRoleRel userRoleRel = new UserRoleRel();
                userRoleRel.setRoleId(roleId);
                userRoleRel.setUserId(userId);
                userRoleRels.add(userRoleRel);
            });
        }
        return saveBatch(userRoleRels);
    }

    @Override
    public List<String> listUserRoleRelByUserId(String userId) {
        QueryWrapper<UserRoleRel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserRoleRel::getUserId, userId);
        List<UserRoleRel> userRoleRels = list(queryWrapper);

        List<String> roleIds = Lists.newArrayList();
        userRoleRels.forEach(userRoleRel -> {
            roleIds.add(userRoleRel.getRoleId());
        });
        return roleIds;
    }
}
