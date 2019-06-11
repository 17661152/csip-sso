package com.expect.csip.iam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.expect.common.domain.TreeNode;
import com.expect.common.enums.PublicEnum;
import com.expect.common.utils.TreeUtil;
import com.expect.csip.iam.domain.Organ;
import com.expect.csip.iam.domain.condition.OrganCondition;
import com.expect.csip.iam.mapper.OrganMapper;
import com.expect.csip.iam.service.OrganService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 组织机构 服务实现类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@Service
public class OrganServiceImpl extends ServiceImpl<OrganMapper, Organ> implements OrganService {


    @Override
    public IPage<Organ> listOrganPage(OrganCondition condition) {
        IPage<Organ> page = condition.buildPage();
        page = baseMapper.listOrganPage(page, condition);
        return page;
    }

    @Override
    public Organ getOrganById(String organId) {
        return getById(organId);
    }

    @Override
    public Map<String, Organ> listOrganMapsByIds(List<String> organIds) {
        Map<String, Organ> resultMap = Maps.newLinkedHashMap();
        Collection<Organ> domains = listByIds(organIds);
        if (CollectionUtils.isEmpty(domains)) {
            return resultMap;
        }

        for (Organ organ : domains) {
            resultMap.put(organ.getOrganId(), organ);
        }
        return resultMap;
    }

    @Override
    @Transactional
    public Boolean deleteOrganByIds(List<String> organIds) {
        return removeByIds(organIds);
    }

    @Override
    @Transactional
    public Boolean deleteOrganById(String organId) {
        List<String> organChildIds = Lists.newArrayList();
        List<String> organIds = Lists.newArrayList();
        organIds.add(organId);

        addOrganChildsByParentOrganId(organChildIds, organId);
        organIds.addAll(organChildIds);
        return removeByIds(organIds);
    }

    @Override
    @Transactional
    public Organ saveOrgan(Organ organ) {
        List<Organ> parentOrgans = Lists.newArrayList();
        addOrganParentsByParentOrganId(parentOrgans, organ.getParentOrganId());
        organ.setIdPath(appendIdPath(parentOrgans, "1"));
        organ.setNamePath(appendNamePath(parentOrgans, organ.getName()));
        organ.setFullName(organ.getName());
        if (StringUtils.isBlank(organ.getParentOrganId())) {
            organ.setParentOrganId(PublicEnum.TREE_ROOT.getStatus());
            organ.setTreeLevel(0);
        } else {
            int size = parentOrgans.size();
            organ.setTreeLevel(size);
        }
        save(organ);
        organ.setIdPath(appendIdPath(parentOrgans, organ.getOrganId()));
        updateById(organ);
        return organ;
    }

    @Override
    @Transactional
    public Boolean updateOrgan(Organ organ) {
        List<Organ> parentOrgans = Lists.newArrayList();
        addOrganParentsByParentOrganId(parentOrgans, organ.getParentOrganId());
        organ.setIdPath(appendIdPath(parentOrgans, organ.getOrganId()));
        organ.setNamePath(appendNamePath(parentOrgans, organ.getName()));
        organ.setFullName(organ.getName());
        if (StringUtils.isBlank(organ.getParentOrganId())) {
            organ.setParentOrganId(PublicEnum.TREE_ROOT.getStatus());
            organ.setTreeLevel(0);
        } else {
            int size = parentOrgans.size();
            organ.setTreeLevel(size);
        }
        return updateById(organ);
    }

    @Override
    public List<TreeNode> listOrganTreeSelect() {
        List<Organ> organs = list(new QueryWrapper<>());
        List<TreeNode> treeNodes = Lists.newArrayList();
        organs.forEach(treeNode -> {
            TreeNode node = new TreeNode();
            node.setId(treeNode.getOrganId());
            node.setValue(treeNode.getOrganId());
            node.setLabel(treeNode.getName());
            node.setpId(treeNode.getParentOrganId());
            treeNodes.add(node);
        });
        return treeNodes;
    }

    @Override
    public List<TreeNode> listOrganTree() {
        List<Organ> organs = list(new QueryWrapper<>());
        List<TreeNode> treeNodes = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(organs)) {
            organs.forEach(organ -> {
                TreeNode treeNode = new TreeNode();
                treeNode.setKey(organ.getOrganId());
                treeNode.setTitle(organ.getName());
                treeNode.setParentId(organ.getParentOrganId());
                treeNodes.add(treeNode);
            });
        }
        return TreeUtil.buildVueTreeByRecursive(treeNodes);
    }

    @Override
    public List<String> listOrganChildsByOrganId(String organId) {
        List<String> organAllIds = Lists.newArrayList();
        addOrganChildsByParentOrganId(organAllIds, organId);
        return organAllIds;
    }

    private void addOrganParentsByParentOrganId(List<Organ> organs, String organId) {
        Organ parentOrgan = getById(organId);
        if (null != parentOrgan) {
            organs.add(parentOrgan);
            addOrganParentsByParentOrganId(organs, parentOrgan.getParentOrganId());
        }
    }

    private void addOrganChildsByParentOrganId(List<String> organIds, String organId) {
        QueryWrapper<Organ> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Organ::getParentOrganId, organId);
        List<Organ> organChilds = list(queryWrapper);
        if (CollectionUtils.isNotEmpty(organChilds)) {
            for (Organ organ : organChilds) {
                organIds.add(organ.getOrganId());
                addOrganChildsByParentOrganId(organIds, organ.getOrganId());
            }
        }
    }

    private String appendNamePath(List<Organ> parentOrgans, String currentOrganName) {
        StringBuilder sb = new StringBuilder();
        if (CollectionUtils.isEmpty(parentOrgans)) {
            sb.append(currentOrganName);
            return sb.toString();
        }
        for (int i = parentOrgans.size() - 1; i >= 0; i--) {
            sb.append(parentOrgans.get(i).getName()).append("/");
        }
        sb.append(currentOrganName);
        return sb.toString();
    }

    private String appendIdPath(List<Organ> parentOrgans, String currentOrganId) {
        StringBuilder sb = new StringBuilder();
        if (CollectionUtils.isEmpty(parentOrgans)) {
            sb.append(currentOrganId);
            return sb.toString();
        }
        for (int i = parentOrgans.size() - 1; i >= 0; i--) {
            sb.append(parentOrgans.get(i).getOrganId()).append("/");
        }
        sb.append(currentOrganId);
        return sb.toString();
    }
}
