package com.expect.csip.iam.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.expect.common.controller.BaseController;
import com.expect.common.domain.PageQueryResult;
import com.expect.csip.iam.domain.AccountNumber;
import com.expect.csip.iam.domain.condition.AccountNumberCondition;
import com.expect.csip.iam.service.AccountNumberService;
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
 * 账号 前端控制器
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@Api(tags = "账号微服务")
@RestController
@RequestMapping("/iam/accountNumber")
public class AccountNumberController extends BaseController {

     @Autowired
     private AccountNumberService accountNumberService;


     @ApiOperation("账号分页列表信息")
     @ApiImplicitParam(name = "condition", value = "账号查询条件", required = true, dataType = "AccountNumberCondition", paramType = "body")
     @PostMapping("/listAccountNumberPage")
	 public PageQueryResult<AccountNumber> listAccountNumberPage(@RequestBody AccountNumberCondition condition) {
        IPage<AccountNumber> pageInfo = accountNumberService.listAccountNumberPage(condition);
		return buildPageQueryResult(pageInfo);
	 }


     @ApiOperation("主键查询账号")
     @ApiImplicitParam(name = "loginName", value = "账号的主键", required = true, dataType = "string", paramType = "body")
     @PostMapping("/getAccountNumberById")
	 public AccountNumber getAccountNumberById(@RequestBody String loginName) {
		return accountNumberService.getAccountNumberById(loginName);
	 }


     @ApiOperation("主键批量查询账号")
     @PostMapping("/listAccountNumberMapsByIds")
	 public Map<String, AccountNumber> listAccountNumberMapsByIds(@ApiParam(name="loginNames",value="账号的主键集合",required = true) @RequestBody List<String> loginNames) {
		return accountNumberService.listAccountNumberMapsByIds(loginNames);
	 }


     @ApiOperation("主键批量删除账号")
     @PostMapping("/deleteAccountNumberByIds")
	 public Boolean deleteAccountNumberByIds(@ApiParam(name="loginNames",value="账号的主键集合",required = true) @RequestBody List<String> loginNames) {
		return accountNumberService.deleteAccountNumberByIds(loginNames);
	 }


     @ApiOperation("主键删除账号")
     @ApiImplicitParam(name = "loginName", value = "账号的主键", required = true, dataType = "string", paramType = "body")
     @PostMapping("/deleteAccountNumberById")
	 public Boolean deleteAccountNumberById(@RequestBody String loginName) {
		return accountNumberService.deleteAccountNumberById(loginName);
	 }


     @ApiOperation("保存账号")
     @ApiImplicitParam(name = "accountNumber", value = "账号信息", required = true, dataType = "AccountNumber", paramType = "body")
     @PostMapping("/saveAccountNumber")
	 public AccountNumber saveAccountNumber(@RequestBody AccountNumber accountNumber) {
		return accountNumberService.saveAccountNumber(accountNumber);
	 }


     @ApiOperation("修改账号")
     @ApiImplicitParam(name = "accountNumber", value = "账号信息", required = true, dataType = "AccountNumber", paramType = "body")
     @PostMapping("/updateAccountNumber")
	 public Boolean updateAccountNumber(@RequestBody AccountNumber accountNumber) {
		return accountNumberService.updateAccountNumber(accountNumber);
	 }
}
