package com.expect.csip.iam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.expect.common.enums.PublicEnum;
import com.expect.csip.iam.domain.AccountNumber;
import com.expect.csip.iam.domain.User;
import com.expect.csip.iam.domain.condition.UserCondition;
import com.expect.csip.iam.mapper.UserMapper;
import com.expect.csip.iam.service.AccountNumberService;
import com.expect.csip.iam.service.UserRoleRelService;
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
 * 用户 服务实现类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private AccountNumberService accountNumberService;

    @Autowired
    private UserRoleRelService userRoleRelService;


    @Override
    public IPage<User> listUserPage(UserCondition condition) {
        IPage<User> page = condition.buildPage();
        IPage<User> pageInfo = baseMapper.listUserPage(page, condition);
		return pageInfo;
    }

    @Override
    //@Cacheable(value = "USER" ,key = "'USER_' + #userId")
    public User getUserById(String userId) {
        User user = getById(userId);
        if (null != user) {
        	// 账号信息
            AccountNumber accountNumber = accountNumberService.getAccountNumberById(user.getLoginName());
            user.setAccountNumber(accountNumber);

            List<String> roleIds = userRoleRelService.listUserRoleRelByUserId(userId);
            user.setRoleIds(roleIds);
        }
        return user;
    }

    @Override
    public Map<String, User> listUserMapsByIds(List<String> userIds) {
        Map<String, User> resultMap = Maps.newLinkedHashMap();
        Collection<User> domains = listByIds(userIds);
        if (CollectionUtils.isEmpty(domains)) {
            return resultMap;
        }

        for (User user : domains) {
            resultMap.put(user.getUserId(), user);
        }
        return resultMap;
    }

    @Override
    @Transactional
    public Boolean deleteUserByIds(List<String> userIds) {
        return removeByIds(userIds);
    }

    @Override
    @Transactional
    public Boolean deleteUserById(String userId) {
        User user = getById(userId);
        accountNumberService.deleteAccountNumberById(user.getLoginName());
        return removeById(userId);
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        AccountNumber accountNumber = user.getAccountNumber();
        accountNumber.setStatus(PublicEnum.ACTIVE.getStatus());
        accountNumberService.saveAccountNumber(accountNumber);

        user.setStatus(PublicEnum.ACTIVE.getStatus());
        user.setLoginName(accountNumber.getLoginName());
        save(user);

        userRoleRelService.saveUserRoleRel(user.getUserId(), user.getRoleIds());
        return user;
    }

    @Override
    @Transactional
    public Boolean updateUser(User user) {
        AccountNumber accountNumber = user.getAccountNumber();
        accountNumberService.updateAccountNumber(accountNumber);

        userRoleRelService.saveUserRoleRel(user.getUserId(), user.getRoleIds());
        return updateById(user);
    }

    @Override
    @Transactional
    public Boolean disableUser(String userId) {
        User user = getById(userId);
        AccountNumber accountNumber = new AccountNumber();
        accountNumber.setLoginName(user.getLoginName());
        accountNumber.setStatus(PublicEnum.UNACTIVE.getStatus());
        accountNumberService.updateAccountNumber(accountNumber);

        user.setStatus(PublicEnum.UNACTIVE.getStatus());
        return updateById(user);
    }

    @Override
    @Transactional
    public Boolean enableUser(String userId) {
        User user = getById(userId);
        AccountNumber accountNumber = new AccountNumber();
        accountNumber.setLoginName(user.getLoginName());
        accountNumber.setStatus(PublicEnum.ACTIVE.getStatus());
        accountNumberService.updateAccountNumber(accountNumber);

        user.setStatus(PublicEnum.ACTIVE.getStatus());
        return updateById(user);
    }

    @Override
    public User getUserByLoginName(String loginName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getLoginName, loginName);
        return getOne(queryWrapper);
    }

	@Override
	public IPage<User> listUserRolePageByRoleId(UserCondition condition) {
		IPage<User> page = condition.buildPage();
		return baseMapper.listUserRolePageByRoleId(page, condition);
	}

    @Override
    @Transactional
    public Boolean updatePassword(User user) {
        AccountNumber accountNumber = new AccountNumber();
        accountNumber.setPassword(user.getNewPassword());
        accountNumber.setLoginName(user.getLoginName());
        accountNumberService.updateAccountNumber(accountNumber);
        return updateById(user);
    }
}
