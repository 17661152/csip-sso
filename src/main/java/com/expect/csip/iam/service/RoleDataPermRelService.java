package com.expect.csip.iam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.expect.csip.iam.domain.RoleDataPermRel;

import java.util.List;


/**
 * <p>
 * 角色数据权限关系 服务类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
public interface RoleDataPermRelService extends IService<RoleDataPermRel> {


    /**
     * 获取用户访问服务的数据权限
     *
     * @param userId
     * @param accessAddress
     * @return
     */
    List<RoleDataPermRel> listUserDataPerms(String userId, String accessAddress);

    /**
     * 删除数据权限关联的角色
     *
     * @param dataPermIds
     * @return
     */
    Boolean deleteRoleDataPermRelByDataPermIds(List<String> dataPermIds);

    /**
     * 更新角色的数据权限
     *
     * @param dataPermId
     * @param dataPermValueIds
     * @return
     */
    Boolean updateRoleDataPermRel(String dataPermId, List<String> dataPermValueIds);

    /**
     * 更新角色的数据权限
     *
     * @param roleId
     * @param dataPermValueIds
     * @return
     */
    Boolean saveRoleDataPermRel(String roleId, List<String> dataPermValueIds);

    /**
     * 角色关联数据权限值ID集合
     *
     * @param roleId
     * @return
     */
    List<String> listRoleDataPermValueIds(String roleId);
}
