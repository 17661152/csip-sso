package com.expect.csip.iam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.expect.csip.iam.domain.UserRoleRel;

import java.util.List;


/**
 * <p>
 * 用户角色关系 服务类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
public interface UserRoleRelService extends IService<UserRoleRel> {


    /**
     * 保存用户角色关系
     *
     * @param userId
     * @param roleIds
     * @return
     */
    Boolean saveUserRoleRel(String userId, List<String> roleIds);

    /**
     * 用户id查询用户角色关系列表
     *
     * @param userId
     * @return roleIds 角色id列表
     */
    List<String> listUserRoleRelByUserId(String userId);
}
