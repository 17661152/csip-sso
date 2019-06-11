package com.expect.csip.iam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.expect.csip.iam.domain.RoleFuncPermRel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色功能权限关系 Mapper 接口
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
public interface RoleFuncPermRelMapper extends BaseMapper<RoleFuncPermRel> {

    /**
     * 查询出角色关联的功能ID集合
     *
     * @param roleFuncPermRel
     * @return
     */
    List<RoleFuncPermRel> listRoleFuncPerms(RoleFuncPermRel roleFuncPermRel);

    /**
     * 删除角色的菜单权限
     *
     * @param roleId
     * @return
     */
    int deleteRoleFuncPermMenuRelByRoleId(@Param("roleId") String roleId, @Param("menuIds") List<String> menuIds);
}
