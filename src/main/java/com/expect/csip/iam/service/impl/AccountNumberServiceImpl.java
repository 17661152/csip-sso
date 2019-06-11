package com.expect.csip.iam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.expect.csip.iam.domain.AccountNumber;
import com.expect.csip.iam.domain.condition.AccountNumberCondition;
import com.expect.csip.iam.mapper.AccountNumberMapper;
import com.expect.csip.iam.service.AccountNumberService;
import com.expect.csip.iam.service.UserService;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 账号 服务实现类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@Service
public class AccountNumberServiceImpl extends ServiceImpl<AccountNumberMapper, AccountNumber> implements AccountNumberService {

    @Autowired
    private UserService userService;

    @Override
    public IPage<AccountNumber> listAccountNumberPage(AccountNumberCondition condition) {
        IPage<AccountNumber> page = condition.buildPage();
        QueryWrapper<AccountNumber> queryWrapper = condition.buildQueryWrapper(AccountNumber.class);
        return page(page, queryWrapper);
    }

    @Override
    public AccountNumber getAccountNumberById(String loginName) {
        return getById(loginName);
    }

    @Override
    public Map<String, AccountNumber> listAccountNumberMapsByIds(List<String> loginNames) {
        Map<String, AccountNumber> resultMap = Maps.newLinkedHashMap();
        Collection<AccountNumber> domains = listByIds(loginNames);
        if (CollectionUtils.isEmpty(domains)) {
            return resultMap;
        }

        for (AccountNumber accountNumber : domains) {
            resultMap.put(accountNumber.getLoginName(), accountNumber);
        }
        return resultMap;
    }

    @Override
    @Transactional
    public Boolean deleteAccountNumberByIds(List<String> loginNames) {
        return removeByIds(loginNames);
    }

    @Override
    @Transactional
    public Boolean deleteAccountNumberById(String loginName) {
        return removeById(loginName);
    }

    @Override
    @Transactional
    public AccountNumber saveAccountNumber(AccountNumber accountNumber) {
        save(accountNumber);
        return accountNumber;
    }

    @Override
    @Transactional
    public Boolean updateAccountNumber(AccountNumber accountNumber) {
        return updateById(accountNumber);
    }

    @Override
    public AccountNumber getAccountNumberAndUserById(String loginName) {
        AccountNumber accountNumber = getAccountNumberById(loginName);
        if (null != accountNumber) {
            accountNumber.setUser(userService.getUserByLoginName(accountNumber.getLoginName()));
        }
        return accountNumber;
    }
}
