package com.expect.csip.iam.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.expect.common.controller.BaseController;
import com.expect.common.domain.PageQueryResult;
import com.expect.csip.iam.domain.Service;
import com.expect.csip.iam.domain.condition.ServiceCondition;
import com.expect.csip.iam.service.ServiceService;
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
 * 服务 前端控制器
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@Api(tags = "服务微服务")
@RestController
@RequestMapping("/iam/service")
public class ServiceController extends BaseController {

    @Autowired
    private ServiceService serviceService;


    @ApiOperation("服务分页列表信息")
    @ApiImplicitParam(name = "condition", value = "服务查询条件", required = true, dataType = "ServiceCondition", paramType = "body")
    @PostMapping("/listServicePage")
    public PageQueryResult<Service> listServicePage(@RequestBody ServiceCondition condition) {
        IPage<Service> pageInfo = serviceService.listServicePage(condition);
        return buildPageQueryResult(pageInfo);
    }


    @ApiOperation("主键查询服务")
    @ApiImplicitParam(name = "serviceId", value = "服务的主键", required = true, dataType = "string", paramType = "body")
    @PostMapping("/getServiceById")
    public Service getServiceById(@RequestBody String serviceId) {
        return serviceService.getServiceById(serviceId);
    }


    @ApiOperation("主键批量查询服务")
    @PostMapping("/listServiceMapsByIds")
    public Map<String, Service> listServiceMapsByIds(@ApiParam(name = "serviceIds", value = "服务的主键集合", required = true) @RequestBody List<String> serviceIds) {
        return serviceService.listServiceMapsByIds(serviceIds);
    }


    @ApiOperation("主键批量删除服务")
    @PostMapping("/deleteServiceByIds")
    public Boolean deleteServiceByIds(@ApiParam(name = "serviceIds", value = "服务的主键集合", required = true) @RequestBody List<String> serviceIds) {
        return serviceService.deleteServiceByIds(serviceIds);
    }

    @ApiOperation("主键删除服务")
    @PostMapping("/deleteServiceById")
    public Boolean deleteServiceById(@ApiParam(name = "serviceId", value = "服务的主键", required = true) @RequestBody String serviceId) {
        return serviceService.deleteServiceById(serviceId);
    }

    @ApiOperation("保存服务")
    @ApiImplicitParam(name = "service", value = "服务信息", required = true, dataType = "Service", paramType = "body")
    @PostMapping("/saveService")
    public Service saveService(@RequestBody Service service) {
        return serviceService.saveService(service);
    }


    @ApiOperation("修改服务")
    @ApiImplicitParam(name = "service", value = "服务信息", required = true, dataType = "Service", paramType = "body")
    @PostMapping("/updateService")
    public Boolean updateService(@RequestBody Service service) {
        return serviceService.updateService(service);
    }
}
