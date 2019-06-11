package com.expect.csip.iam.controller;


import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.security.token.SSOToken;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.expect.common.controller.BaseController;
import com.expect.common.domain.PageQueryResult;
import com.expect.csip.iam.domain.AccountNumber;
import com.expect.csip.iam.domain.User;
import com.expect.csip.iam.domain.condition.UserCondition;
import com.expect.csip.iam.service.AccountNumberService;
import com.expect.csip.iam.service.UserService;
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
import java.util.Map;


/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@Api(tags = "用户微服务")
@RestController
@RequestMapping("/iam/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountNumberService accountNumberService;


    @ApiOperation("用户分页列表信息")
    @ApiImplicitParam(name = "condition", value = "用户查询条件", required = true, dataType = "UserCondition", paramType = "body")
    @PostMapping("/listUserPage")
    public PageQueryResult<User> listUserPage(@RequestBody UserCondition condition) {
        IPage<User> pageInfo = userService.listUserPage(condition);
        return buildPageQueryResult(pageInfo);
    }


    @ApiOperation("主键查询用户")
    @ApiImplicitParam(name = "userId", value = "用户的主键", required = true, dataType = "string", paramType = "body")
    @PostMapping("/getUserById")
    public User getUserById(@RequestBody String userId) {
        return userService.getUserById(userId);
    }


    @ApiOperation("主键批量查询用户")
    @PostMapping("/listUserMapsByIds")
    public Map<String, User> listUserMapsByIds(@ApiParam(name = "userIds", value = "用户的主键集合", required = true) @RequestBody List<String> userIds) {
        return userService.listUserMapsByIds(userIds);
    }


    @ApiOperation("主键批量删除用户")
    @PostMapping("/deleteUserByIds")
    public Boolean deleteUserByIds(@ApiParam(name = "userIds", value = "用户的主键集合", required = true) @RequestBody List<String> userIds) {
        return userService.deleteUserByIds(userIds);
    }


    @ApiOperation("主键删除用户")
    @ApiImplicitParam(name = "userId", value = "用户的主键", required = true, dataType = "string", paramType = "body")
    @PostMapping("/deleteUserById")
    public Boolean deleteUserById(@RequestBody String userId) {
        return userService.deleteUserById(userId);
    }


    @ApiOperation("保存用户")
    @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "User", paramType = "body")
    @PostMapping("/saveUser")
    public User saveUser(@RequestBody User user) {
        AccountNumber accountNumber = user.getAccountNumber();
        AccountNumber account = accountNumberService.getAccountNumberById(accountNumber.getLoginName());
        if (null != account) {
            throw new ApiException("账号已存在，请重新输入！");
        }
        return userService.saveUser(user);
    }


    @ApiOperation("修改用户")
    @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "User", paramType = "body")
    @PostMapping("/updateUser")
    public Boolean updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @ApiOperation("修改用户密码")
    @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "User", paramType = "body")
    @PostMapping("/updatePassword")
    public Boolean updatePassword(@RequestBody User user) {
        SSOToken ssoToken = SSOHelper.attrToken(request);
        user.setUserId(ssoToken.getId());
        AccountNumber accountNumber = accountNumberService.getAccountNumberById(user.getLoginName());
        if (!accountNumber.getPassword().equals(user.getPassword())) {
            throw new ApiException("旧密码不匹配");
        }
        return userService.updatePassword(user);
    }

    @ApiOperation("禁用用户")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "string", paramType = "body")
    @PostMapping("/disableUser")
    public Boolean disableUser(@RequestBody String userId) {
        return userService.disableUser(userId);
    }

    @ApiOperation("启用用户")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "string", paramType = "body")
    @PostMapping("/enableUser")
    public Boolean enableUser(@RequestBody String userId) {
        return userService.enableUser(userId);
    }

    @ApiOperation("角色下的用户分页列表信息")
    @ApiImplicitParam(name = "condition", value = "用户查询条件", required = true, dataType = "UserCondition", paramType = "body")
    @PostMapping("/listUserRolePageByRoleId")
    public PageQueryResult<User> listUserRolePageByRoleId(@RequestBody UserCondition condition) {
        IPage<User> pageInfo = userService.listUserRolePageByRoleId(condition);
        return buildPageQueryResult(pageInfo);
    }
}
