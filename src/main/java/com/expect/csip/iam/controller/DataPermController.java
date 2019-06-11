package com.expect.csip.iam.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.expect.common.controller.BaseController;
import com.expect.common.domain.PageQueryResult;
import com.expect.csip.iam.domain.DataPerm;
import com.expect.csip.iam.domain.condition.DataPermCondition;
import com.expect.csip.iam.domain.DataPermValue;
import com.expect.csip.iam.service.DataPermService;
import com.expect.csip.iam.service.MenuDataPermRelService;
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
 * 数据权限 前端控制器
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@Api(tags = "数据权限微服务")
@RestController
@RequestMapping("/iam/dataPerm")
public class DataPermController extends BaseController {

    @Autowired
    private DataPermService dataPermService;

    @Autowired
    private MenuDataPermRelService menuDataPermRelService;


    @ApiOperation("数据权限分页列表信息")
    @ApiImplicitParam(name = "condition", value = "数据权限查询条件", required = true, dataType = "DataPermCondition", paramType = "body")
    @PostMapping("/listDataPermPage")
    public PageQueryResult<DataPerm> listDataPermPage(@RequestBody DataPermCondition condition) {
        IPage<DataPerm> pageInfo = dataPermService.listDataPermPage(condition);
        return buildPageQueryResult(pageInfo);
    }


    @ApiOperation("主键查询数据权限")
    @ApiImplicitParam(name = "dataPermId", value = "数据权限的主键", required = true, dataType = "string", paramType = "body")
    @PostMapping("/getDataPermById")
    public DataPerm getDataPermById(@RequestBody String dataPermId) {
        return dataPermService.getDataPermById(dataPermId);
    }

    @ApiOperation("主键批量删除数据权限")
    @PostMapping("/deleteDataPermByIds")
    public Boolean deleteDataPermByIds(@ApiParam(name = "dataPermIds", value = "数据权限的主键集合", required = true) @RequestBody List<String> dataPermIds) {
        return dataPermService.deleteDataPermByIds(dataPermIds);
    }

    @ApiOperation("主键批量数据权限")
    @PostMapping("/deleteDataPermById")
    public Boolean deleteDataPermById(@ApiParam(name = "dataPermId", value = "数据权限的主键", required = true) @RequestBody String dataPermId) {
        return dataPermService.deleteDataPermById(dataPermId);
    }

    @ApiOperation("保存数据权限")
    @ApiImplicitParam(name = "dataPerm", value = "数据权限信息", required = true, dataType = "DataPerm", paramType = "body")
    @PostMapping("/saveDataPerm")
    public DataPerm saveDataPerm(@RequestBody DataPerm dataPerm) {
        return dataPermService.saveDataPerm(dataPerm);
    }


    @ApiOperation("修改数据权限")
    @ApiImplicitParam(name = "dataPerm", value = "数据权限信息", required = true, dataType = "DataPerm", paramType = "body")
    @PostMapping("/updateDataPerm")
    public Boolean updateDataPerm(@RequestBody DataPerm dataPerm) {
        return dataPermService.updateDataPerm(dataPerm);
    }

    @ApiOperation("禁用元素")
    @ApiImplicitParam(name = "dataPermId", value = "数据权限ID", required = true, dataType = "string", paramType = "body")
    @PostMapping("/disableDataPerm")
    public Boolean disableDataPerm(@RequestBody String dataPermId) {
        return dataPermService.disableDataPerm(dataPermId);
    }

    @ApiOperation("启用元素")
    @ApiImplicitParam(name = "dataPermId", value = "数据权限ID", required = true, dataType = "string", paramType = "body")
    @PostMapping("/enableDataPerm")
    public Boolean enableDataPerm(@RequestBody String dataPermId) {
        return dataPermService.enableDataPerm(dataPermId);
    }

    @ApiOperation("菜单关联的数据权限列表")
    @ApiImplicitParam(name = "menuId", value = "菜单ID", required = true, dataType = "string", paramType = "body")
    @PostMapping("/listMenuDataPermValues")
    public PageQueryResult<DataPermValue> listMenuDataPermValues(@RequestBody String menuId) {
        List<DataPermValue> dataPermValues = menuDataPermRelService.listMenuDataPermValues(menuId);
        PageQueryResult<DataPermValue> pageQueryResult = new PageQueryResult<>();
        pageQueryResult.setRecords(dataPermValues);
        pageQueryResult.setTotalRecord(dataPermValues.size());
        pageQueryResult.setPage(1);
        return pageQueryResult;
    }
}
