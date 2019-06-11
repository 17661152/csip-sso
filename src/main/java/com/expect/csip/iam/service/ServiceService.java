package com.expect.csip.iam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.expect.csip.iam.domain.Service;
import com.expect.csip.iam.domain.condition.ServiceCondition;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 服务 服务类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
public interface ServiceService extends IService<Service> {

    /**
     * 服务分页列表
     *
     * @param condition
     * @return
     */
    IPage<Service> listServicePage(ServiceCondition condition);

    /**
     * 主键查询服务
     *
     * @param serviceId
     * @return
     */
    Service getServiceById(String serviceId);

    /**
     * 访问地址查询服务
     *
     * @param accessAddress
     * @return
     */
    Service getServiceByAccessAddress(String accessAddress);

    /**
     * 主键批量查询服务
     *
     * @param serviceIds
     * @return
     */
    Map<String, Service> listServiceMapsByIds(List<String> serviceIds);

    /**
     * 主键批量删除服务
     *
     * @param serviceIds
     * @return
     */
    Boolean deleteServiceByIds(List<String> serviceIds);

    /**
     * 主键删除服务
     *
     * @param serviceId
     * @return
     */
    Boolean deleteServiceById(String serviceId);

    /**
     * 保存服务
     *
     * @param service
     * @return
     */
    Service saveService(Service service);

    /**
     * 修改服务
     *
     * @param service
     * @return
     */
    Boolean updateService(Service service);

    /**
     * 判断当前用户是否拥有访问地址权限
     *
     * @param userId
     * @param accessAddress
     * @param currentRoute
     * @return
     */
    Boolean checkServicePerm(String userId, String accessAddress, String currentRoute);
}
