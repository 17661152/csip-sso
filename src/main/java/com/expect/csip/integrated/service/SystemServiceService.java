package com.expect.csip.integrated.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.expect.csip.integrated.domain.SystemService;
import com.expect.csip.integrated.domain.condition.SystemServiceCondition;

import java.util.List;
import java.util.Map;


/**
 * 集成系统服务表(SystemService)服务接口
 *
 * @author jack.zeng
 * @since 2019-05-30 14:06:44
 */
public interface SystemServiceService extends IService<SystemService> {


   /**
    * 集成系统服务表分页列表
    * @param condition
    * @return
    */
    IPage<SystemService> listSystemServicePage(SystemServiceCondition condition);


   /**
    * 主键查询集成系统服务表
    * @param systemId
    * @return
    */
    SystemService getSystemServiceById(String systemId);


   /**
    * 主键批量查询集成系统服务表
    * @param systemIds
    * @return
    */
    Map<String, SystemService> listSystemServiceMapsByIds(List<String> systemIds);


   /**
    * 主键批量删除集成系统服务表
    * @param systemIds
    * @return
    */
    Boolean deleteSystemServiceByIds(List<String> systemIds);


   /**
    * 主键删除集成系统服务表
    * @param systemId
    * @return
    */
    Boolean deleteSystemServiceById(String systemId);


   /**
    * 保存集成系统服务表
    * @param systemService
    * @return
    */
    SystemService saveSystemService(SystemService systemService);


   /**
    * 修改集成系统服务表
    * @param systemService
    * @return
    */
    Boolean updateSystemService(SystemService systemService);
}