package com.expect.csip.iam.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.expect.common.constant.PublicConstant;
import com.expect.common.enums.FuncEnum;
import com.expect.common.enums.PublicEnum;
import com.expect.csip.iam.domain.Element;
import com.expect.csip.iam.domain.FuncPerm;
import com.expect.csip.iam.domain.condition.ElementCondition;
import com.expect.csip.iam.mapper.ElementMapper;
import com.expect.csip.iam.service.ElementService;
import com.expect.csip.iam.service.FuncPermService;
import com.expect.csip.iam.service.FuncPermSvcRelService;
import com.expect.csip.iam.service.RoleFuncPermRelService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 元素 服务实现类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@Service
public class ElementServiceImpl extends ServiceImpl<ElementMapper, Element> implements ElementService {

    @Autowired
    private FuncPermService funcPermService;

    @Autowired
    private FuncPermSvcRelService funcPermSvcRelService;

    @Autowired
    private RoleFuncPermRelService roleFuncPermRelService;

    @Override
    public IPage<Element> listElementPage(ElementCondition condition) {
        IPage<Element> page = condition.buildPage();
        page = baseMapper.listElementPage(page, condition);
        return page;
    }

    @Override
    public Element getElementById(String elementId) {
        Element element = getById(elementId);
        if (null != element) {
            FuncPerm funcPerm = funcPermService.getFuncPermById(elementId);
            element.setName(funcPerm.getName());
            element.setFuncPermId(funcPerm.getFuncPermId());
            element.setIsEnable(funcPerm.getIsEnable());
            element.setType(funcPerm.getType());
            element.setSortNumber(funcPerm.getSortNumber());
            element.setDescr(funcPerm.getDescr());

            FuncPerm pageFuncPerm = funcPermService.getFuncPermById(element.getPageId());
            element.setPageName(pageFuncPerm.getName());

            List<com.expect.csip.iam.domain.Service> services = funcPermSvcRelService.listFuncPermService(element.getElementId());
            element.setServices(services);
        }
        return element;
    }

    @Override
    public Map<String, Element> listElementMapsByIds(List<String> elementIds) {
        Map<String, Element> resultMap = Maps.newLinkedHashMap();
        Collection<Element> domains = listByIds(elementIds);
        if (CollectionUtils.isEmpty(domains)) {
            return resultMap;
        }

        for (Element element : domains) {
            resultMap.put(element.getElementId(), element);
        }
        return resultMap;
    }

    @Override
    @Transactional
    public Boolean deleteElementByIds(List<String> elementIds) {
        return removeByIds(elementIds);
    }

    @Override
    @Transactional
    public Boolean deleteElementById(String elementId) {
        funcPermService.deleteFuncPermById(elementId);
        List<String> permIds = Lists.newArrayList();
        permIds.add(elementId);
        funcPermSvcRelService.deleteFuncPermSvcRelByFuncPermIds(permIds);
        roleFuncPermRelService.deleteRoleFuncPermRelByFuncPermIds(permIds);
        return removeById(elementId);
    }

    @Override
    @Transactional
    public Element saveElement(Element element) {
        //先保存功能权限
        FuncPerm funcPerm = BeanUtil.toBean(element, FuncPerm.class);
        funcPerm.setType(FuncEnum.ELEMENT.getType());
        funcPerm.setIsEnable(PublicEnum.ACTIVE.getStatus());
        funcPerm = funcPermService.saveFuncPerm(funcPerm);

        element.setElementId(funcPerm.getFuncPermId());
        save(element);

        funcPermSvcRelService.saveFuncPermSvcRel(element.getElementId(), element.getServices());
        return element;
    }

    @Override
    @Transactional
    public Boolean updateElement(Element element) {
        FuncPerm funcPerm = BeanUtil.toBean(element, FuncPerm.class);
        funcPerm.setFuncPermId(element.getElementId());
        funcPerm.setType(FuncEnum.ELEMENT.getType());
        funcPermService.updateFuncPerm(funcPerm);

        funcPermSvcRelService.saveFuncPermSvcRel(element.getElementId(), element.getServices());
        return updateById(element);
    }

    @Override
    @Transactional
    public Boolean disableElement(String elementId) {
        List<String> elementIds = Lists.newArrayList();
        elementIds.add(elementId);
        return funcPermService.disableFuncPerm(elementIds);
    }

    @Override
    @Transactional
    public Boolean enableElement(String elementId) {
        List<String> elementIds = Lists.newArrayList();
        elementIds.add(elementId);
        return funcPermService.enableFuncPerm(elementIds);
    }

    @Override
    public Integer countElementByPageId(String pageId) {
        QueryWrapper<Element> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Element::getPageId, pageId);
        return count(queryWrapper);
    }

    @Override
    public List<String> listAuthElement(String userId) {
        List<String> elementCodes = Lists.newArrayList();
        List<Element> elements = null;
        if (StringUtils.equals(userId, PublicConstant.UNAUTHORIZED_USER_ID)) {
            elements = baseMapper.listAuthElement(null);
        } else {
            elements = baseMapper.listAuthElement(userId);
        }
        if (CollectionUtils.isNotEmpty(elements)) {
            elements.forEach(element -> {
                elementCodes.add(element.getElementCode());
            });
        }
        return elementCodes;
    }
}
