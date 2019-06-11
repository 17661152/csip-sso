package com.expect.csip.iam.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.expect.common.controller.BaseController;
import com.expect.common.domain.PageQueryResult;
import com.expect.csip.iam.domain.Page;
import com.expect.csip.iam.domain.condition.PageCondition;
import com.expect.csip.iam.service.ElementService;
import com.expect.csip.iam.service.PageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 * 页面 前端控制器
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@Api(tags = "页面微服务")
@RestController
@RequestMapping("/iam/page")
public class PageController extends BaseController {

    @Autowired
    private PageService pageService;

    @Autowired
    private ElementService elementService;

    @ApiOperation("页面分页列表信息")
    @ApiImplicitParam(name = "condition", value = "页面查询条件", required = true, dataType = "PageCondition", paramType = "body")
    @PostMapping("/listPagePage")
    public PageQueryResult<Page> listPagePage(@RequestBody PageCondition condition) {
        IPage<Page> pageInfo = pageService.listPagePage(condition);
        return buildPageQueryResult(pageInfo);
    }


    @ApiOperation("主键查询页面")
    @ApiImplicitParam(name = "pageId", value = "页面的主键", required = true, dataType = "string", paramType = "body")
    @PostMapping("/getPageById")
    public Page getPageById(@RequestBody String pageId) {
        return pageService.getPageById(pageId);
    }

    @ApiOperation("主键删除页面")
    @ApiImplicitParam(name = "pageId", value = "页面的主键", required = true, dataType = "string", paramType = "body")
    @PostMapping("/deletePageById")
    public Boolean deletePageById(@RequestBody String pageId) {
        Integer total = elementService.countElementByPageId(pageId);
        if (total > 0) {
            throw new ApiException("请先删除页面所关联的元素列表");
        }
        return pageService.deletePageById(pageId);
    }


    @ApiOperation("保存页面")
    @ApiImplicitParam(name = "page", value = "页面信息", required = true, dataType = "Page", paramType = "body")
    @PostMapping("/savePage")
    public Page savePage(@RequestBody Page page) {
        return pageService.savePage(page);
    }


    @ApiOperation("修改页面")
    @ApiImplicitParam(name = "page", value = "页面信息", required = true, dataType = "Page", paramType = "body")
    @PostMapping("/updatePage")
    public Boolean updatePage(@RequestBody Page page) {
        return pageService.updatePage(page);
    }

    @ApiOperation("菜单关联的页面列表")
    @ApiImplicitParam(name = "menuIds", value = "菜单ID集合", required = true, paramType = "body")
    @PostMapping("/listMenuPage")
    public List<Page> listMenuPage(@RequestBody List<String> menuIds) {
        return pageService.listMenuPage(menuIds);
    }

    @ApiOperation("禁用页面")
    @ApiImplicitParam(name = "pageId", value = "页面ID", required = true, dataType = "string", paramType = "body")
    @PostMapping("/disablePage")
    public Boolean disablePage(@RequestBody String pageId) {
        return pageService.disablePage(pageId);
    }

    @ApiOperation("启用页面")
    @ApiImplicitParam(name = "menuId", value = "页面ID", required = true, dataType = "string", paramType = "body")
    @PostMapping("/enablePage")
    public Boolean enablePage(@RequestBody String pageId) {
        return pageService.enablePage(pageId);
    }
}
