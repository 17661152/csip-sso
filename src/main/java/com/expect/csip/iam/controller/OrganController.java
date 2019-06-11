package com.expect.csip.iam.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.expect.common.controller.BaseController;
import com.expect.common.domain.PageQueryResult;
import com.expect.common.domain.TreeNode;
import com.expect.csip.iam.domain.Organ;
import com.expect.csip.iam.domain.condition.OrganCondition;
import com.expect.csip.iam.service.OrganService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 * 组织机构 前端控制器
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@Api(tags = "组织机构微服务")
@RestController
@RequestMapping("/iam/organ")
public class OrganController extends BaseController {

    @Autowired
    private OrganService organService;


    @ApiOperation("组织机构分页列表信息")
    @ApiImplicitParam(name = "condition", value = "组织机构查询条件", required = true, dataType = "OrganCondition", paramType = "body")
    @PostMapping("/listOrganPage")
    public PageQueryResult<Organ> listOrganPage(@RequestBody OrganCondition condition) {
        IPage<Organ> pageInfo = organService.listOrganPage(condition);
        return buildPageQueryResult(pageInfo);
    }


    @ApiOperation("主键查询组织机构")
    @ApiImplicitParam(name = "organId", value = "组织机构的主键", required = true, dataType = "string", paramType = "body")
    @PostMapping("/getOrganById")
    public Organ getOrganById(@RequestBody String organId) {
        return organService.getOrganById(organId);
    }


    @ApiOperation("主键批量删除组织机构")
    @PostMapping("/deleteOrganByIds")
    public Boolean deleteOrganByIds(@ApiParam(name = "organIds", value = "组织机构的主键集合", required = true) @RequestBody List<String> organIds) {
        return organService.deleteOrganByIds(organIds);
    }


    @ApiOperation("主键删除组织机构")
    @ApiImplicitParam(name = "organId", value = "组织机构的主键", required = true, dataType = "string", paramType = "body")
    @PostMapping("/deleteOrganById")
    public Boolean deleteOrganById(@RequestBody String organId) {
        return organService.deleteOrganById(organId);
    }


    @ApiOperation("保存组织机构")
    @ApiImplicitParam(name = "organ", value = "组织机构信息", required = true, dataType = "Organ", paramType = "body")
    @PostMapping("/saveOrgan")
    public Organ saveOrgan(@RequestBody Organ organ) {
        return organService.saveOrgan(organ);
    }


    @ApiOperation("修改组织机构")
    @ApiImplicitParam(name = "organ", value = "组织机构信息", required = true, dataType = "Organ", paramType = "body")
    @PostMapping("/updateOrgan")
    public Boolean updateOrgan(@RequestBody Organ organ) {
        if (StringUtils.equals(organ.getOrganId(), organ.getParentOrganId())) {
            throw new ApiException("父组织不能选择本身");
        }

        List<String> organChilds = organService.listOrganChildsByOrganId(organ.getOrganId());
        if (organChilds.contains(organ.getParentOrganId())) {
            throw new ApiException("父组织不能选择当前组织的子组织");
        }
        return organService.updateOrgan(organ);
    }


    @ApiOperation("组织机构下拉树选择列表")
    @PostMapping("/listOrganTreeSelect")
    public List<TreeNode> listOrganTreeSelect() {
        return organService.listOrganTreeSelect();
    }

    @ApiOperation("组织树列表")
    @PostMapping("/listOrganTree")
    public List<TreeNode> listOrganTree() {
        return organService.listOrganTree();
    }
}
