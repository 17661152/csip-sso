package com.expect.csip.iam.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.expect.common.controller.BaseController;
import com.expect.common.domain.PageQueryResult;
import com.expect.csip.iam.domain.Element;
import com.expect.csip.iam.domain.condition.ElementCondition;
import com.expect.csip.iam.service.ElementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 元素 前端控制器
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@Api(tags = "元素微服务")
@RestController
@RequestMapping("/iam/element")
public class ElementController extends BaseController {

    @Autowired
    private ElementService elementService;


    @ApiOperation("元素分页列表信息")
    @ApiImplicitParam(name = "condition", value = "元素查询条件", required = true, dataType = "ElementCondition", paramType = "body")
    @PostMapping("/listElementPage")
    public PageQueryResult<Element> listElementPage(@RequestBody ElementCondition condition) {
        IPage<Element> pageInfo = elementService.listElementPage(condition);
        return buildPageQueryResult(pageInfo);
    }


    @ApiOperation("主键查询元素")
    @ApiImplicitParam(name = "elementId", value = "元素的主键", required = true, dataType = "string", paramType = "body")
    @PostMapping("/getElementById")
    public Element getElementById(@RequestBody String elementId) {
        return elementService.getElementById(elementId);
    }

    @ApiOperation("主键删除元素")
    @ApiImplicitParam(name = "elementId", value = "元素的主键", required = true, dataType = "string", paramType = "body")
    @PostMapping("/deleteElementById")
    public Boolean deleteElementById(@RequestBody String elementId) {
        return elementService.deleteElementById(elementId);
    }

    @ApiOperation("保存元素")
    @ApiImplicitParam(name = "element", value = "元素信息", required = true, dataType = "Element", paramType = "body")
    @PostMapping("/saveElement")
    public Element saveElement(@RequestBody Element element) {
        return elementService.saveElement(element);
    }


    @ApiOperation("修改元素")
    @ApiImplicitParam(name = "element", value = "元素信息", required = true, dataType = "Element", paramType = "body")
    @PostMapping("/updateElement")
    public Boolean updateElement(@RequestBody Element element) {
        return elementService.updateElement(element);
    }

    @ApiOperation("禁用元素")
    @ApiImplicitParam(name = "elementId", value = "元素ID", required = true, dataType = "string", paramType = "body")
    @PostMapping("/disableElement")
    public Boolean disableElement(@RequestBody String elementId) {
        return elementService.disableElement(elementId);
    }

    @ApiOperation("启用元素")
    @ApiImplicitParam(name = "elementId", value = "元素ID", required = true, dataType = "string", paramType = "body")
    @PostMapping("/enableElement")
    public Boolean enableElement(@RequestBody String elementId) {
        return elementService.enableElement(elementId);
    }
}
