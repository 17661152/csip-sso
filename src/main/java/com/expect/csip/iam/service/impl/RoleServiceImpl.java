package com.expect.csip.iam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.expect.csip.iam.domain.Role;
import com.expect.csip.iam.domain.RoleAuth;
import com.expect.csip.iam.domain.condition.RoleCondition;
import com.expect.csip.iam.mapper.RoleMapper;
import com.expect.csip.iam.service.RoleDataPermRelService;
import com.expect.csip.iam.service.RoleFuncPermRelService;
import com.expect.csip.iam.service.RoleService;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleFuncPermRelService roleFuncPermRelService;

    @Autowired
    private RoleDataPermRelService roleDataPermRelService;

    public IPage<Role> listRolePage(RoleCondition condition) {
        IPage<Role> page = condition.buildPage();
        QueryWrapper<Role> queryWrapper = condition.buildQueryWrapper(Role.class);
        IPage<Role> pageInfo = page(page, queryWrapper);
        return pageInfo;
    }

    @Override
    public Role getRoleById(String roleId) {
        Role role = getById(roleId);
        return role;
    }

    @Override
    public Map<String, Role> listRoleMapsByIds(List<String> roleIds) {
        Map<String, Role> resultMap = Maps.newLinkedHashMap();
        Collection<Role> domains = listByIds(roleIds);
        if (CollectionUtils.isEmpty(domains)) {
            return resultMap;
        }

        for (Role role : domains) {
            resultMap.put(role.getRoleId(), role);
        }
        return resultMap;
    }

    @Override
    @Transactional
    public Boolean deleteRoleByIds(List<String> roleIds) {
        return removeByIds(roleIds);
    }

    @Override
    @Transactional
    public Boolean deleteRoleById(String roleId) {
        return removeById(roleId);
    }

    @Override
    @Transactional
    public Role saveRole(Role role) {
        save(role);
        return role;
    }

    @Override
    @Transactional
    public Boolean updateRole(Role role) {
        return updateById(role);
    }

    @Override
    @Transactional
    public Boolean roleAuth(RoleAuth roleAuth) {
        Boolean funcPermResult = roleFuncPermRelService.saveRoleFuncPermRel(roleAuth);
        Boolean roleDataPerm = roleDataPermRelService.saveRoleDataPermRel(roleAuth.getRoleId(), roleAuth.getDataPermValueIds());
        return funcPermResult && roleDataPerm;
    }

    @Override
    public List<Role> listAllRole() {
        return list(new QueryWrapper<>());
    }

    @Override
    public RoleAuth getRoleCheckPerms(String roleId) {
        RoleAuth roleAuth = new RoleAuth();
        RoleAuth funcPerm = roleFuncPermRelService.getRoleFuncPermIds(roleId);
        List<String> dataPermValueChecks = roleDataPermRelService.listRoleDataPermValueIds(roleId);

        roleAuth.setFuncPermIds(funcPerm.getFuncPermIds());
        roleAuth.setHalfFuncPermIds(funcPerm.getHalfFuncPermIds());
        roleAuth.setDataPermValueIds(dataPermValueChecks);
        return roleAuth;
    }
}
