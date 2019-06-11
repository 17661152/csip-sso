package com.expect.csip.iam.controller;

import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.enums.TokenOrigin;
import com.baomidou.kisso.security.token.SSOToken;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.expect.common.controller.BaseController;
import com.expect.common.enums.PublicEnum;
import com.expect.csip.iam.domain.AccountNumber;
import com.expect.csip.iam.domain.User;
import com.expect.csip.iam.service.AccountNumberService;
import com.expect.csip.iam.service.ElementService;
import com.expect.csip.iam.service.MenuService;
import com.expect.csip.iam.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录
 */
@Api(tags = "授权微服务")
@RestController
@RequestMapping("/iam/authenticate")
public class AuthenticateController extends BaseController {

    @Autowired
    private AccountNumberService accountNumberService;

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private ElementService elementService;


    @ApiOperation("退出登录")
    @Login(action = Action.Skip)
    @PostMapping("/logout")
    public void logout() {
        SSOHelper.clearLogin(request, response);
    }

    @ApiOperation("获取登录用户信息")
    @PostMapping("/getUserInfo")
    public User getUserInfo() {
        SSOToken ssoToken = SSOHelper.attrToken(request);
        User user = userService.getById(ssoToken.getId());
        user.setMenuRouters(menuService.listAuthVueRouter(user.getUserId()));
        user.setElements(elementService.listAuthElement(user.getUserId()));
        return user;
    }

    @ApiOperation("登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "账号", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string", paramType = "query")
    })
    @Login(action = Action.Skip)
    @PostMapping("/login")
    public String login(@RequestParam("loginName") String loginName, @RequestParam("password") String password) {
        AccountNumber loadAccountNumber = accountNumberService.getAccountNumberAndUserById(loginName);
        if (null == loadAccountNumber) {
            throw new ApiException("账号或密码错误");
        }

        if (!password.equals(loadAccountNumber.getPassword())) {
            throw new ApiException("账号或密码错误");
        }

        if (null == loadAccountNumber.getUser()) {
            throw new ApiException("账号已被禁用");
        }

        if (loadAccountNumber.getStatus().equals(PublicEnum.UNACTIVE.getStatus())) {
            throw new ApiException("账号已被禁用");
        }

        SSOToken token = SSOToken.create();
        token.setId(loadAccountNumber.getUser().getUserId());
        token.setIssuer(loadAccountNumber.getLoginName());
        token.setOrigin(TokenOrigin.HTML5);
        token.setIp(request);
        token.setUserAgent(request);
        return token.getToken();
    }
}
