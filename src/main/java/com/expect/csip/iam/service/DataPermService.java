package com.expect.csip.iam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.expect.csip.iam.domain.DataPerm;
import com.expect.csip.iam.domain.condition.DataPermCondition;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 数据权限 服务类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
public interface DataPermService extends IService<DataPerm> {

    /**
     * 数据权限分页列表
     *
     * @param condition
     * @return
     */
    IPage<DataPerm> listDataPermPage(DataPermCondition condition);

    /**
     * 主键查询数据权限
     *
     * @param dataPermId
     * @return
     */
    DataPerm getDataPermById(String dataPermId);

    /**
     * 主键批量查询数据权限
     *
     * @param dataPermIds
     * @return
     */
    Map<String, DataPerm> listDataPermMapsByIds(List<String> dataPermIds);

    /**
     * 主键批量删除数据权限
     *
     * @param dataPermIds
     * @return
     */
    Boolean deleteDataPermByIds(List<String> dataPermIds);

    /**
     * 主键删除数据权限
     *
     * @param dataPermId
     * @return
     */
    Boolean deleteDataPermById(String dataPermId);

    /**
     * 保存数据权限
     *
     * @param dataPerm
     * @return
     */
    DataPerm saveDataPerm(DataPerm dataPerm);

    /**
     * 修改数据权限
     *
     * @param dataPerm
     * @return
     */
    Boolean updateDataPerm(DataPerm dataPerm);

    /**
     * 获取当前用户所拥有的数据权限表达式
     *
     * @param userId
     * @param accessAddress
     * @return
     */
    String getConditionExpression(String userId, String accessAddress);

    /**
     * 删除服务下的数据权限
     *
     * @param serviceIds
     * @return
     */
    Boolean deleteDataPermByServiceIds(List<String> serviceIds);

    /**
     * 禁用数据权限
     * @param dataPermId
     * @return
     */
    Boolean disableDataPerm(String dataPermId);

    /**
     * 启用数据权限
     * @param dataPermId
     * @return
     */
    Boolean enableDataPerm(String dataPermId);
}
