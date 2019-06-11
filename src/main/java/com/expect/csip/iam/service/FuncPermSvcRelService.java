package com.expect.csip.iam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.expect.csip.iam.domain.FuncPermSvcRel;
import com.expect.csip.iam.domain.Service;

import java.util.List;


/**
 * <p>
 * 功能权限服务关系 服务类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
public interface FuncPermSvcRelService extends IService<FuncPermSvcRel> {

    /**
     * 批量删除服务与权限关系
     *
     * @param serviceIds
     * @return
     */
    Boolean deleteFuncPermSvcRelByServiceIds(List<String> serviceIds);

    /**
     * 保存菜单与服务关系
     *
     * @param funcId
     * @param services
     * @return
     */
    Boolean saveFuncPermSvcRel(String funcId, List<Service> services);

    /**
     * 删除功能权限下的服务关系
     * @param funcPermIds
     * @return
     */
    Boolean deleteFuncPermSvcRelByFuncPermIds(List<String> funcPermIds);

    /**
     * 功能权限下的服务列表
     *
     * @param funcPermId
     * @return
     */
    List<Service> listFuncPermService(String funcPermId);
}
