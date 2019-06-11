package com.expect.csip.iam.domain.condition;

import com.expect.common.domain.condition.BaseCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * <p>
 * 数据权限查询条件
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@ApiModel(description = "数据权限查询条件")
public class DataPermCondition extends BaseCondition {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "是否启用：0-禁用；1-启用")
    private String isEnable;

    @ApiModelProperty(value = "服务名称")
    private String serviceName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
