package com.expect.csip.iam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.expect.common.domain.TreeNode;
import com.expect.csip.iam.domain.Organ;
import com.expect.csip.iam.domain.condition.OrganCondition;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 组织机构 服务类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
public interface OrganService extends IService<Organ> {

    /**
     * 组织机构分页列表
     *
     * @param condition
     * @return
     */
    IPage<Organ> listOrganPage(OrganCondition condition);

    /**
     * 主键查询组织机构
     *
     * @param organId
     * @return
     */
    Organ getOrganById(String organId);

    /**
     * 主键批量查询组织机构
     *
     * @param organIds
     * @return
     */
    Map<String, Organ> listOrganMapsByIds(List<String> organIds);

    /**
     * 主键批量删除组织机构
     *
     * @param organIds
     * @return
     */
    Boolean deleteOrganByIds(List<String> organIds);

    /**
     * 主键删除组织机构
     *
     * @param organId
     * @return
     */
    Boolean deleteOrganById(String organId);

    /**
     * 保存组织机构
     *
     * @param organ
     * @return
     */
    Organ saveOrgan(Organ organ);

    /**
     * 修改组织机构
     *
     * @param organ
     * @return
     */
    Boolean updateOrgan(Organ organ);

    /**
     * 组织机构下拉树选择列表
     *
     * @return
     */
    List<TreeNode> listOrganTreeSelect();

    /**
     * 组织机构树列表
     *
     * @return
     */
    List<TreeNode> listOrganTree();

    /**
     * 当前组织下的子组织ID列表
     *
     * @param organId
     * @return
     */
    List<String> listOrganChildsByOrganId(String organId);
}
