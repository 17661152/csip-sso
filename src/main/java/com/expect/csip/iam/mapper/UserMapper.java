package com.expect.csip.iam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.expect.csip.iam.domain.User;
import com.expect.csip.iam.domain.condition.UserCondition;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
public interface UserMapper extends BaseMapper<User> {

	/**
	 * 用户分页列表
	 * @param page
	 * @param condition
	 * @return
	 */
	IPage<User> listUserPage(IPage<User> page, @Param("condition") UserCondition condition);

	/**
	 * 查询角色下面的用户分页列表
	 * @param page
	 * @param condition
	 * @return
	 */
	IPage<User> listUserRolePageByRoleId(IPage<User> page, @Param("condition") UserCondition condition);
}
