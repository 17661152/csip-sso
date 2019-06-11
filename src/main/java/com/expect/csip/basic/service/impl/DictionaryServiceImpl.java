package com.expect.csip.basic.service.impl;

import com.expect.csip.basic.domain.Dictionary;
import com.expect.csip.basic.mapper.DictionaryMapper;
import com.expect.csip.basic.service.DictionaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.expect.csip.basic.domain.condition.DictionaryCondition;

import com.google.common.collect.Lists;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;


/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-04-08 22:55:14
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {


    @Override
    public IPage<Dictionary> listDictionaryPage(DictionaryCondition condition) {
        IPage<Dictionary> page = condition.buildPage();
        QueryWrapper<Dictionary> queryWrapper = condition.buildQueryWrapper(Dictionary.class);
        return page(page, queryWrapper);
    }

    @Override
    public Dictionary getDictionaryById(String dictId) {
        return getById(dictId);
    }

    @Override
    public Map<String, Dictionary> listDictionaryMapsByIds(List<String> dictIds) {
        Map<String, Dictionary> resultMap = Maps.newLinkedHashMap();
        Collection<Dictionary> domains = listByIds(dictIds);
        if (CollectionUtils.isEmpty(domains)) {
            return resultMap;
        }

        for (Dictionary dictionary : domains) {
            resultMap.put(dictionary.getDictId(), dictionary);
        }
        return resultMap;
    }

    @Override
    @Transactional
    public Boolean deleteDictionaryByIds(List<String> dictIds) {
        return removeByIds(dictIds);
    }

    @Override
    @Transactional
    public Boolean deleteDictionaryById(String dictId) {
        return removeById(dictId);
    }

    @Override
    @Transactional
    public Dictionary saveDictionary(Dictionary dictionary) {
        save(dictionary);
        return dictionary;
    }

    @Override
    @Transactional
    public Boolean updateDictionary(Dictionary dictionary) {
        return updateById(dictionary);
    }

    @Override
    public List<Dictionary> listDictionaryByCategory(String category) {
        QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Dictionary::getCategory, category);
        return list(queryWrapper);
    }

    @Override
    public Map<String, List<Dictionary>> listDictionaryByCategorys(List<String> categorys) {
        Map<String, List<Dictionary>> dictMaps = Maps.newLinkedHashMap();
        QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(Dictionary::getCategory, categorys);
        List<Dictionary> dictionaries = list(queryWrapper);
        if (CollectionUtils.isNotEmpty(dictionaries)) {
            dictionaries.forEach(dictionary -> {
                if (dictMaps.get(dictionary.getCategory()) == null) {
                    dictMaps.put(dictionary.getCategory(), Lists.newArrayList());
                }
                dictMaps.get(dictionary.getCategory()).add(dictionary);
            });
        }
        return dictMaps;
    }
}
