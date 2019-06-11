package com.expect.csip.iam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.expect.csip.iam.domain.RoleDataPermRel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色数据权限关系 Mapper 接口
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
public interface RoleDataPermRelMapper extends BaseMapper<RoleDataPermRel> {

    /**
     * 获取用户访问服务的数据权限
     *
     * @param userId
     * @param accessAddress
     * @return
     */
    List<RoleDataPermRel> listUserDataPerms(@Param("userId") String userId, @Param("accessAddress") String accessAddress);

    /**
     * 角色的数据权限列表
     * @param roleId
     * @return
     */
    List<RoleDataPermRel> listRoleDataPermRelByRoleId(String roleId);
}
