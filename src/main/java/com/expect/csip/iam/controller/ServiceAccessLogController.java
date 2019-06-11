package com.expect.csip.iam.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.expect.common.controller.BaseController;
import com.expect.common.domain.PageQueryResult;
import com.expect.csip.iam.domain.ServiceAccessLog;
import com.expect.csip.iam.domain.condition.ServiceAccessLogCondition;
import com.expect.csip.iam.service.ServiceAccessLogService;
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
 * 服务访问日志 前端控制器
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@Api(tags = "服务访问日志微服务")
@RestController
@RequestMapping("/iam/serviceAccessLog")
public class ServiceAccessLogController extends BaseController {

    @Autowired
    private ServiceAccessLogService serviceAccessLogService;


    @ApiOperation("服务访问日志分页列表信息")
    @ApiImplicitParam(name = "condition", value = "服务访问日志查询条件", required = true, dataType = "ServiceAccessLogCondition", paramType = "body")
    @PostMapping("/listServiceAccessLogPage")
    public PageQueryResult<ServiceAccessLog> listServiceAccessLogPage(@RequestBody ServiceAccessLogCondition condition) {
        IPage<ServiceAccessLog> pageInfo = serviceAccessLogService.listServiceAccessLogPage(condition);
        return buildPageQueryResult(pageInfo);
    }

    @ApiOperation("主键批量删除服务访问日志")
    @PostMapping("/deleteServiceAccessLogByIds")
    public Boolean deleteServiceAccessLogByIds(@ApiParam(name = "logIds", value = "服务访问日志的主键集合", required = true) @RequestBody List<String> logIds) {
        return serviceAccessLogService.deleteServiceAccessLogByIds(logIds);
    }


    @ApiOperation("主键删除服务访问日志")
    @ApiImplicitParam(name = "logId", value = "服务访问日志的主键", required = true, dataType = "string", paramType = "body")
    @PostMapping("/deleteServiceAccessLogById")
    public Boolean deleteServiceAccessLogById(@RequestBody String logId) {
        return serviceAccessLogService.deleteServiceAccessLogById(logId);
    }

    @ApiOperation("主键查询服务访问日志")
    @ApiImplicitParam(name = "logId", value = "服务访问日志的主键", required = true, dataType = "string", paramType = "body")
    @PostMapping("/getServiceAccessLogById")
    public ServiceAccessLog getServiceAccessLogById(@RequestBody String logId) {
        return serviceAccessLogService.getServiceAccessLogById(logId);
    }

    @ApiOperation("保存服务访问日志")
    @ApiImplicitParam(name = "serviceAccessLog", value = "服务访问日志信息", required = true, dataType = "ServiceAccessLog", paramType = "body")
    @PostMapping("/saveServiceAccessLog")
    public ServiceAccessLog saveServiceAccessLog(@RequestBody ServiceAccessLog serviceAccessLog) {
        return serviceAccessLogService.saveServiceAccessLog(serviceAccessLog);
    }


    @ApiOperation("修改服务访问日志")
    @ApiImplicitParam(name = "serviceAccessLog", value = "服务访问日志信息", required = true, dataType = "ServiceAccessLog", paramType = "body")
    @PostMapping("/updateServiceAccessLog")
    public Boolean updateServiceAccessLog(@RequestBody ServiceAccessLog serviceAccessLog) {
        return serviceAccessLogService.updateServiceAccessLog(serviceAccessLog);
    }
}
