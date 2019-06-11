package com.expect.csip.iam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.expect.csip.iam.domain.User;
import com.expect.csip.iam.domain.condition.UserCondition;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
public interface UserService extends IService<User> {

    /**
     * 用户分页列表
     *
     * @param condition
     * @return
     */
    IPage<User> listUserPage(UserCondition condition);

    /**
     * 主键查询用户
     *
     * @param userId
     * @return
     */
    User getUserById(String userId);

    /**
     * 主键批量查询用户
     *
     * @param userIds
     * @return
     */
    Map<String, User> listUserMapsByIds(List<String> userIds);

    /**
     * 主键批量删除用户
     *
     * @param userIds
     * @return
     */
    Boolean deleteUserByIds(List<String> userIds);

    /**
     * 主键删除用户
     *
     * @param userId
     * @return
     */
    Boolean deleteUserById(String userId);

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    User saveUser(User user);

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    Boolean updateUser(User user);

    /**
     * 禁用用户
     *
     * @param userId
     * @return
     */
    Boolean disableUser(String userId);

    /**
     * 启用用户
     *
     * @param userId
     * @return
     */
    Boolean enableUser(String userId);

    /**
     * 登录名查询用户
     *
     * @param loginName
     * @return
     */
    User getUserByLoginName(String loginName);

    /**
     * 查询角色下面的用户分页列表
     *
     * @param condition
     * @return
     */
    IPage<User> listUserRolePageByRoleId(UserCondition condition);

    /**
     * 更新用户密码
     * @param user
     * @return
     */
    Boolean updatePassword(User user);
}
