package com.expect.csip.integrated.controller;



import com.expect.csip.integrated.domain.SystemService;
import com.expect.csip.integrated.domain.condition.SystemServiceCondition;
import com.expect.csip.integrated.service.SystemServiceService;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.expect.common.controller.BaseController;
import com.expect.common.domain.PageQueryResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;
import java.util.Map;



/**
 * 集成系统服务表(SystemService)控制层
 *
 * @author jack.zeng
 * @since 2019-05-30 14:06:44
 */
@Api(tags = "集成系统服务表微服务")
@RestController
@RequestMapping("/integrated/systemService")
public class SystemServiceController extends BaseController {

    /**
     * 服务对象
     */
    @Autowired
    private SystemServiceService systemServiceService;


    @ApiOperation("集成系统服务表分页列表信息")
    @ApiImplicitParam(name = "condition", value = "集成系统服务表查询条件", required = true, dataType = "SystemServiceCondition", paramType = "body")
    @PostMapping("/listSystemServicePage")
    public PageQueryResult<SystemService> listSystemServicePage(@RequestBody SystemServiceCondition condition) {
        IPage<SystemService> pageInfo = systemServiceService.listSystemServicePage(condition);
        return buildPageQueryResult(pageInfo);
    }


    @ApiOperation("主键查询集成系统服务表")
    @ApiImplicitParam(name = "systemId", value = "集成系统服务表的主键", required = true, dataType = "string", paramType = "body")
    @PostMapping("/getSystemServiceById")
    public SystemService getSystemServiceById(@RequestBody String systemId) {
        return systemServiceService.getSystemServiceById(systemId);
    }


    @ApiOperation("主键批量查询集成系统服务表")
    @PostMapping("/listSystemServiceMapsByIds")
    public Map<String, SystemService> listSystemServiceMapsByIds(@ApiParam(name="systemIds",value="集成系统服务表的主键集合",required = true) @RequestBody List<String> systemIds) {
        return systemServiceService.listSystemServiceMapsByIds(systemIds);
    }


    @ApiOperation("主键批量删除集成系统服务表")
    @PostMapping("/deleteSystemServiceByIds")
    public Boolean deleteSystemServiceByIds(@ApiParam(name="systemIds",value="集成系统服务表的主键集合",required = true) @RequestBody List<String> systemIds) {
        return systemServiceService.deleteSystemServiceByIds(systemIds);
    }


    @ApiOperation("主键删除集成系统服务表")
    @ApiImplicitParam(name = "systemId", value = "集成系统服务表的主键", required = true, dataType = "string", paramType = "body")
    @PostMapping("/deleteSystemServiceById")
    public Boolean deleteSystemServiceById(@RequestBody String systemId) {
        return systemServiceService.deleteSystemServiceById(systemId);
    }


    @ApiOperation("保存集成系统服务表")
    @ApiImplicitParam(name = "systemService", value = "集成系统服务表信息", required = true, dataType = "SystemService", paramType = "body")
    @PostMapping("/saveSystemService")
    public SystemService saveSystemService(@RequestBody SystemService systemService) {
        return systemServiceService.saveSystemService(systemService);
    }


    @ApiOperation("修改集成系统服务表")
    @ApiImplicitParam(name = "systemService", value = "集成系统服务表信息", required = true, dataType = "SystemService", paramType = "body")
    @PostMapping("/updateSystemService")
    public Boolean updateSystemService(@RequestBody SystemService systemService) {
        return systemServiceService.updateSystemService(systemService);
    }

    @ApiOperation("集成系统服务表列表信息")
    @PostMapping("/listSystemService")
    public List<SystemService> listSystemService() {
        return systemServiceService.list();
    }
}