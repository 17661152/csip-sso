package com.expect.csip.iam.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.expect.common.controller.BaseController;
import com.expect.common.domain.PageQueryResult;
import com.expect.csip.iam.domain.condition.DataPermValueCondition;
import com.expect.csip.iam.domain.DataPermValue;
import com.expect.csip.iam.service.DataPermValueService;
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
 * 数据权限值 前端控制器
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@Api(tags = "数据权限值微服务")
@RestController
@RequestMapping("/iam/dataPermValue")
public class DataPermValueController extends BaseController {

     @Autowired
     private DataPermValueService dataPermValueService;


     @ApiOperation("数据权限值分页列表信息")
     @ApiImplicitParam(name = "condition", value = "数据权限值查询条件", required = true, dataType = "DataPermValueCondition", paramType = "body")
     @PostMapping("/listDataPermValuePage")
	 public PageQueryResult<DataPermValue> listDataPermValuePage(@RequestBody DataPermValueCondition condition) {
        IPage<DataPermValue> pageInfo = dataPermValueService.listDataPermValuePage(condition);
		return buildPageQueryResult(pageInfo);
	 }
}
