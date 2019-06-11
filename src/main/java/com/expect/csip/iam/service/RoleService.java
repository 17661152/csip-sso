package com.expect.csip.iam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.expect.csip.iam.domain.Role;
import com.expect.csip.iam.domain.RoleAuth;
import com.expect.csip.iam.domain.condition.RoleCondition;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
public interface RoleService extends IService<Role> {

    /**
     * 角色分页列表
     *
     * @param condition
     * @return
     */
    IPage<Role> listRolePage(RoleCondition condition);

    /**
     * 主键查询角色
     *
     * @param roleId
     * @return
     */
    Role getRoleById(String roleId);

    /**
     * 主键批量查询角色
     *
     * @param roleIds
     * @return
     */
    Map<String, Role> listRoleMapsByIds(List<String> roleIds);

    /**
     * 主键批量删除角色
     *
     * @param roleIds
     * @return
     */
    Boolean deleteRoleByIds(List<String> roleIds);

    /**
     * 主键删除角色
     *
     * @param roleId
     * @return
     */
    Boolean deleteRoleById(String roleId);

    /**
     * 保存角色
     *
     * @param role
     * @return
     */
    Role saveRole(Role role);

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    Boolean updateRole(Role role);

    /**
     * 角色授权
     *
     * @param roleAuth
     * @return
     */
    Boolean roleAuth(RoleAuth roleAuth);

    /**
     * 查询所有的角色列表
     *
     * @return
     */
    List<Role> listAllRole();

    /**
     * 角色选中过的权限列表
     *
     * @param roleId
     * @return
     */
    RoleAuth getRoleCheckPerms(String roleId);
}
