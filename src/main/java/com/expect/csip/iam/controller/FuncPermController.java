package com.expect.csip.iam.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.expect.common.controller.BaseController;
import com.expect.common.domain.PageQueryResult;
import com.expect.csip.iam.domain.FuncPerm;
import com.expect.csip.iam.domain.condition.FuncPermCondition;
import com.expect.csip.iam.service.FuncPermService;
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
 * 功能权限 前端控制器
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@Api(tags = "功能权限微服务")
@RestController
@RequestMapping("/iam/funcPerm")
public class FuncPermController extends BaseController {

     @Autowired
     private FuncPermService funcPermService;


     @ApiOperation("功能权限分页列表信息")
     @ApiImplicitParam(name = "condition", value = "功能权限查询条件", required = true, dataType = "FuncPermCondition", paramType = "body")
     @PostMapping("/listFuncPermPage")
	 public PageQueryResult<FuncPerm> listFuncPermPage(@RequestBody FuncPermCondition condition) {
        IPage<FuncPerm> pageInfo = funcPermService.listFuncPermPage(condition);
		return buildPageQueryResult(pageInfo);
	 }


     @ApiOperation("主键查询功能权限")
     @ApiImplicitParam(name = "funcPermId", value = "功能权限的主键", required = true, dataType = "string", paramType = "body")
     @PostMapping("/getFuncPermById")
	 public FuncPerm getFuncPermById(@RequestBody String funcPermId) {
		return funcPermService.getFuncPermById(funcPermId);
	 }


     @ApiOperation("主键批量查询功能权限")
     @PostMapping("/listFuncPermMapsByIds")
	 public Map<String, FuncPerm> listFuncPermMapsByIds(@ApiParam(name="funcPermIds",value="功能权限的主键集合",required = true) @RequestBody List<String> funcPermIds) {
		return funcPermService.listFuncPermMapsByIds(funcPermIds);
	 }


     @ApiOperation("主键批量删除功能权限")
     @PostMapping("/deleteFuncPermByIds")
	 public Boolean deleteFuncPermByIds(@ApiParam(name="funcPermIds",value="功能权限的主键集合",required = true) @RequestBody List<String> funcPermIds) {
		return funcPermService.deleteFuncPermByIds(funcPermIds);
	 }


     @ApiOperation("主键删除功能权限")
     @ApiImplicitParam(name = "funcPermId", value = "功能权限的主键", required = true, dataType = "string", paramType = "body")
     @PostMapping("/deleteFuncPermById")
	 public Boolean deleteFuncPermById(@RequestBody String funcPermId) {
		return funcPermService.deleteFuncPermById(funcPermId);
	 }


     @ApiOperation("保存功能权限")
     @ApiImplicitParam(name = "funcPerm", value = "功能权限信息", required = true, dataType = "FuncPerm", paramType = "body")
     @PostMapping("/saveFuncPerm")
	 public FuncPerm saveFuncPerm(@RequestBody FuncPerm funcPerm) {
		return funcPermService.saveFuncPerm(funcPerm);
	 }


     @ApiOperation("修改功能权限")
     @ApiImplicitParam(name = "funcPerm", value = "功能权限信息", required = true, dataType = "FuncPerm", paramType = "body")
     @PostMapping("/updateFuncPerm")
	 public Boolean updateFuncPerm(@RequestBody FuncPerm funcPerm) {
		return funcPermService.updateFuncPerm(funcPerm);
	 }
}
