package com.expect.csip.iam.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.expect.common.controller.BaseController;
import com.expect.common.domain.PageQueryResult;
import com.expect.csip.iam.domain.Role;
import com.expect.csip.iam.domain.RoleAuth;
import com.expect.csip.iam.domain.condition.RoleCondition;
import com.expect.csip.iam.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@Api(tags = "角色微服务")
@RestController
@RequestMapping("/iam/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;


    @ApiOperation("角色分页列表信息")
    @ApiImplicitParam(name = "condition", value = "角色查询条件", required = true, dataType = "RoleCondition", paramType = "body")
    @PostMapping("/listRolePage")
    public PageQueryResult<Role> listRolePage(@RequestBody RoleCondition condition) {
        IPage<Role> pageInfo = roleService.listRolePage(condition);
        return buildPageQueryResult(pageInfo);
    }

    @ApiOperation("所有角色列表信息")
    @PostMapping("/listAllRole")
    public List<Role> listAllRole() {
        return roleService.listAllRole();
    }

    @ApiOperation("主键查询角色")
    @ApiImplicitParam(name = "roleId", value = "角色的主键", required = true, dataType = "string", paramType = "body")
    @PostMapping("/getRoleById")
    public Role getRoleById(@RequestBody String roleId) {
        return roleService.getRoleById(roleId);
    }


    @ApiOperation("主键批量删除角色")
    @PostMapping("/deleteRoleByIds")
    public Boolean deleteRoleByIds(@ApiParam(name = "roleIds", value = "角色的主键集合", required = true) @RequestBody List<String> roleIds) {
        return roleService.deleteRoleByIds(roleIds);
    }


    @ApiOperation("主键删除角色")
    @ApiImplicitParam(name = "roleId", value = "角色的主键", required = true, dataType = "string", paramType = "body")
    @PostMapping("/deleteRoleById")
    public Boolean deleteRoleById(@RequestBody String roleId) {
        return roleService.deleteRoleById(roleId);
    }


    @ApiOperation("保存角色")
    @ApiImplicitParam(name = "role", value = "角色信息", required = true, dataType = "Role", paramType = "body")
    @PostMapping("/saveRole")
    public Role saveRole(@RequestBody Role role) {
        return roleService.saveRole(role);
    }


    @ApiOperation("修改角色")
    @ApiImplicitParam(name = "role", value = "角色信息", required = true, dataType = "Role", paramType = "body")
    @PostMapping("/updateRole")
    public Boolean updateRole(@RequestBody Role role) {
        return roleService.updateRole(role);
    }

    @ApiOperation("角色授权")
    @ApiImplicitParam(name = "roleAuth", value = "角色信息", required = true, dataType = "RoleAuth", paramType = "body")
    @PostMapping("/saveRoleAuth")
    public Boolean saveRoleAuth(@RequestBody RoleAuth roleAuth) {
        return roleService.roleAuth(roleAuth);
    }

    @ApiOperation("角色选中过的权限列表")
    @ApiImplicitParam(name = "roleId", value = "角色ID", required = true, dataType = "string", paramType = "body")
    @PostMapping("/getRoleCheckPerms")
    public RoleAuth getRoleCheckPerms(@RequestBody String roleId) {
        return roleService.getRoleCheckPerms(roleId);
    }
}
