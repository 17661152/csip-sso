package com.expect.csip.basic.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.expect.csip.basic.domain.Dictionary;
import com.expect.csip.basic.domain.condition.DictionaryCondition;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-04-08 22:55:14
 */
public interface DictionaryService extends IService<Dictionary> {

    /**
     * 数据字典分页列表
     *
     * @param condition
     * @return
     */
    IPage<Dictionary> listDictionaryPage(DictionaryCondition condition);

    /**
     * 主键查询数据字典
     *
     * @param dictId
     * @return
     */
    Dictionary getDictionaryById(String dictId);

    /**
     * 主键批量查询数据字典
     *
     * @param dictIds
     * @return
     */
    Map<String, Dictionary> listDictionaryMapsByIds(List<String> dictIds);

    /**
     * 主键批量删除数据字典
     *
     * @param dictIds
     * @return
     */
    Boolean deleteDictionaryByIds(List<String> dictIds);

    /**
     * 主键删除数据字典
     *
     * @param dictId
     * @return
     */
    Boolean deleteDictionaryById(String dictId);

    /**
     * 保存数据字典
     *
     * @param dictionary
     * @return
     */
    Dictionary saveDictionary(Dictionary dictionary);

    /**
     * 修改数据字典
     *
     * @param dictionary
     * @return
     */
    Boolean updateDictionary(Dictionary dictionary);

    /**
     * 按分类查询字典列表
     *
     * @param category
     * @return
     */
    List<Dictionary> listDictionaryByCategory(String category);

    /**
     * 按分类批量查询字典列表
     *
     * @param categorys
     * @return
     */
    Map<String, List<Dictionary>> listDictionaryByCategorys(List<String> categorys);
}
