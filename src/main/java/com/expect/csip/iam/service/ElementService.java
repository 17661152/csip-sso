package com.expect.csip.iam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.expect.csip.iam.domain.Element;
import com.expect.csip.iam.domain.condition.ElementCondition;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 元素 服务类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
public interface ElementService extends IService<Element> {

    /**
     * 元素分页列表
     *
     * @param condition
     * @return
     */
    IPage<Element> listElementPage(ElementCondition condition);

    /**
     * 主键查询元素
     *
     * @param elementId
     * @return
     */
    Element getElementById(String elementId);

    /**
     * 主键批量查询元素
     *
     * @param elementIds
     * @return
     */
    Map<String, Element> listElementMapsByIds(List<String> elementIds);

    /**
     * 主键批量删除元素
     *
     * @param elementIds
     * @return
     */
    Boolean deleteElementByIds(List<String> elementIds);

    /**
     * 主键删除元素
     *
     * @param elementId
     * @return
     */
    Boolean deleteElementById(String elementId);

    /**
     * 保存元素
     *
     * @param element
     * @return
     */
    Element saveElement(Element element);

    /**
     * 修改元素
     *
     * @param element
     * @return
     */
    Boolean updateElement(Element element);

    /**
     * 统计页面下元素的数量
     *
     * @param pageId
     * @return
     */
    Integer countElementByPageId(String pageId);

    /**
     * 权限元素列表
     *
     * @param userId
     * @return
     */
    List<String> listAuthElement(String userId);

    /**
     * 禁用元素
     * @param elementId
     * @return
     */
    Boolean disableElement(String elementId);

    /**
     * 启用元素
     * @param elementId
     * @return
     */
    Boolean enableElement(String elementId);
}
