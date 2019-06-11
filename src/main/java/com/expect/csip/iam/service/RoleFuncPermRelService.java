package com.expect.csip.iam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.expect.csip.iam.domain.RoleAuth;
import com.expect.csip.iam.domain.RoleFuncPermRel;

import java.util.List;


/**
 * <p>
 * 角色功能权限关系 服务类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
public interface RoleFuncPermRelService extends IService<RoleFuncPermRel> {


    /**
     * 保存角色功能权限关系
     *
     * @param roleAuth
     * @return
     */
    Boolean saveRoleFuncPermRel(RoleAuth roleAuth);

    /**
     * 功能权限批量删除角色功能权限关系
     *
     * @param funcPermIds
     * @return
     */
    Boolean deleteRoleFuncPermRelByFuncPermIds(List<String> funcPermIds);

    /**
     * 查询出角色关联的功能ID集合
     *
     * @param roleId
     * @return
     */
    RoleAuth getRoleFuncPermIds(String roleId);
}
