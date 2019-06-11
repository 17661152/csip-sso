package com.expect.csip.iam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.expect.csip.iam.domain.FuncPermSvcRel;
import com.expect.csip.iam.domain.Service;

import java.util.List;

/**
 * <p>
 * 功能权限服务关系 Mapper 接口
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
public interface FuncPermSvcRelMapper extends BaseMapper<FuncPermSvcRel> {

    /**
     * 功能权限下的服务列表
     *
     * @param funcPermId
     * @return
     */
    List<Service> listFuncPermService(String funcPermId);
}
