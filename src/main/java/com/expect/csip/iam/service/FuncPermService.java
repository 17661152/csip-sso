package com.expect.csip.iam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.expect.csip.iam.domain.FuncPerm;
import com.expect.csip.iam.domain.condition.FuncPermCondition;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 功能权限 服务类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
public interface FuncPermService extends IService<FuncPerm> {

    /**
	 * 功能权限分页列表
	 * @param condition
	 * @return
	 */
	IPage<FuncPerm> listFuncPermPage(FuncPermCondition condition);

	/**
	 * 主键查询功能权限
	 * @param funcPermId
	 * @return
	 */
    FuncPerm getFuncPermById(String funcPermId);

    /**
	 * 主键批量查询功能权限
	 * @param funcPermIds
	 * @return
	 */
    Map<String, FuncPerm> listFuncPermMapsByIds(List<String> funcPermIds);

    /**
	 * 主键批量删除功能权限
	 * @param funcPermIds
	 * @return
	 */
	Boolean deleteFuncPermByIds(List<String> funcPermIds);

    /**
	 * 主键删除功能权限
	 * @param funcPermId
	 * @return
	 */
	Boolean deleteFuncPermById(String funcPermId);

    /**
	 * 保存功能权限
	 * @param funcPerm
	 * @return
	 */
    FuncPerm saveFuncPerm(FuncPerm funcPerm);

	/**
	 * 修改功能权限
	 * @param funcPerm
	 * @return
	 */
	Boolean updateFuncPerm(FuncPerm funcPerm);

	/**
	 * 禁用功能权限
	 * @param funcPermIds
	 * @return
	 */
	Boolean disableFuncPerm(List<String> funcPermIds);

	/**
	 * 启用功能权限
	 * @param funcPermIds
	 * @return
	 */
	Boolean enableFuncPerm(List<String> funcPermIds);
}
