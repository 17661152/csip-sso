package com.expect.csip.iam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.expect.csip.iam.domain.AccountNumber;
import com.expect.csip.iam.domain.condition.AccountNumberCondition;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 账号 服务类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
public interface AccountNumberService extends IService<AccountNumber> {

    /**
	 * 账号分页列表
	 * @param condition
	 * @return
	 */
	IPage<AccountNumber> listAccountNumberPage(AccountNumberCondition condition);

	/**
	 * 主键查询账号
	 * @param loginName
	 * @return
	 */
    AccountNumber getAccountNumberById(String loginName);

    /**
	 * 主键批量查询账号
	 * @param loginNames
	 * @return
	 */
    Map<String, AccountNumber> listAccountNumberMapsByIds(List<String> loginNames);

    /**
	 * 主键批量删除账号
	 * @param loginNames
	 * @return
	 */
	Boolean deleteAccountNumberByIds(List<String> loginNames);

    /**
	 * 主键删除账号
	 * @param loginName
	 * @return
	 */
	Boolean deleteAccountNumberById(String loginName);

    /**
	 * 保存账号
	 * @param accountNumber
	 * @return
	 */
    AccountNumber saveAccountNumber(AccountNumber accountNumber);

	/**
	 * 修改账号
	 * @param accountNumber
	 * @return
	 */
	Boolean updateAccountNumber(AccountNumber accountNumber);

	/**
	 * 登录名查询账号，用户
	 * @param loginName
	 * @return
	 */
	AccountNumber getAccountNumberAndUserById(String loginName);
}
