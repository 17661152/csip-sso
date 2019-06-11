package com.expect.csip.basic.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.expect.common.controller.BaseController;
import com.expect.common.domain.PageQueryResult;
import com.expect.csip.basic.domain.Dictionary;
import com.expect.csip.basic.domain.condition.DictionaryCondition;
import com.expect.csip.basic.service.DictionaryService;
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
 * 数据字典 前端控制器
 * </p>
 *
 * @author jack.zeng
 * @since 2019-04-08 22:55:14
 */
@Api(tags = "数据字典微服务")
@RestController
@RequestMapping("/basic/dictionary")
public class DictionaryController extends BaseController {

    @Autowired
    private DictionaryService dictionaryService;


    @ApiOperation("数据字典分页列表信息")
    @ApiImplicitParam(name = "condition", value = "数据字典查询条件", required = true, dataType = "DictionaryCondition", paramType = "body")
    @PostMapping("/listDictionaryPage")
    public PageQueryResult<Dictionary> listDictionaryPage(@RequestBody DictionaryCondition condition) {
        IPage<Dictionary> pageInfo = dictionaryService.listDictionaryPage(condition);
        return buildPageQueryResult(pageInfo);
    }


    @ApiOperation("主键查询数据字典")
    @ApiImplicitParam(name = "dictId", value = "数据字典的主键", required = true, dataType = "string", paramType = "body")
    @PostMapping("/getDictionaryById")
    public Dictionary getDictionaryById(@RequestBody String dictId) {
        return dictionaryService.getDictionaryById(dictId);
    }


    @ApiOperation("主键批量查询数据字典")
    @PostMapping("/listDictionaryMapsByIds")
    public Map<String, Dictionary> listDictionaryMapsByIds(@ApiParam(name = "dictIds", value = "数据字典的主键集合", required = true) @RequestBody List<String> dictIds) {
        return dictionaryService.listDictionaryMapsByIds(dictIds);
    }


    @ApiOperation("主键批量删除数据字典")
    @PostMapping("/deleteDictionaryByIds")
    public Boolean deleteDictionaryByIds(@ApiParam(name = "dictIds", value = "数据字典的主键集合", required = true) @RequestBody List<String> dictIds) {
        return dictionaryService.deleteDictionaryByIds(dictIds);
    }


    @ApiOperation("主键删除数据字典")
    @ApiImplicitParam(name = "dictId", value = "数据字典的主键", required = true, dataType = "string", paramType = "body")
    @PostMapping("/deleteDictionaryById")
    public Boolean deleteDictionaryById(@RequestBody String dictId) {
        return dictionaryService.deleteDictionaryById(dictId);
    }


    @ApiOperation("保存数据字典")
    @ApiImplicitParam(name = "dictionary", value = "数据字典信息", required = true, dataType = "Dictionary", paramType = "body")
    @PostMapping("/saveDictionary")
    public Dictionary saveDictionary(@RequestBody Dictionary dictionary) {
        return dictionaryService.saveDictionary(dictionary);
    }


    @ApiOperation("修改数据字典")
    @ApiImplicitParam(name = "dictionary", value = "数据字典信息", required = true, dataType = "Dictionary", paramType = "body")
    @PostMapping("/updateDictionary")
    public Boolean updateDictionary(@RequestBody Dictionary dictionary) {
        return dictionaryService.updateDictionary(dictionary);
    }

    @ApiOperation("按分类查询字典列表")
    @ApiImplicitParam(name = "category", value = "字典分类", required = true, dataType = "string", paramType = "body")
    @PostMapping("/listDictionaryByCategory")
    public List<Dictionary> listDictionaryByCategory(@RequestBody String category) {
        return dictionaryService.listDictionaryByCategory(category);
    }

    @ApiOperation("按分类批量查询字典列表")
    @PostMapping("/listDictionaryByCategorys")
    public Map<String, List<Dictionary>> listDictionaryByCategorys(@RequestBody List<String> categorys) {
        return dictionaryService.listDictionaryByCategorys(categorys);
    }
}
