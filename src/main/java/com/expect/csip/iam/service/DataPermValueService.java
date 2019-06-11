package com.expect.csip.iam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.expect.csip.iam.domain.condition.DataPermValueCondition;
import com.expect.csip.iam.domain.DataPermValue;

import java.util.List;


/**
 * <p>
 * 数据权限值 服务类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
public interface DataPermValueService extends IService<DataPermValue> {

    /**
     * 数据权限值分页列表
     *
     * @param condition
     * @return
     */
    IPage<DataPermValue> listDataPermValuePage(DataPermValueCondition condition);

    /**
     * 删除服务下的数据权限值
     *
     * @param serviceIds
     * @return
     */
    Boolean deleteDataPermValueByServiceIds(List<String> serviceIds);

    /**
     * 更新数据权限下的权限值
     *
     * @param dataPermId
     * @param dataPermValues
     * @return
     */
    Boolean updateDataPermValue(String dataPermId, List<DataPermValue> dataPermValues);

    /**
     * 数据权限值列表
     *
     * @param condition
     * @return
     */
    List<DataPermValue> listDataPermValue(DataPermValueCondition condition);
}
