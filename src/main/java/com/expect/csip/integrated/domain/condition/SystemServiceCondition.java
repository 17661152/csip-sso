package com.expect.csip.integrated.domain.condition;

import com.expect.common.domain.condition.BaseCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 集成系统服务表(SystemService)查询条件
 *
 * @author jack.zeng
 * @since 2019-05-30 14:06:44
 */
@ApiModel(description = "集成系统服务表")
public class SystemServiceCondition extends BaseCondition {

    private static final long serialVersionUID = 1L;

    /**
     * 系统英文编码
     */
    @ApiModelProperty(value = "系统英文编码")
    private String code;
    /**
     * 系统服务名称
     */
    @ApiModelProperty(value = "系统服务名称")
    private String name;
    /**
     * 访问地址
     */
    @ApiModelProperty(value = "访问地址")
    private String accessAddress;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccessAddress() {
        return accessAddress;
    }

    public void setAccessAddress(String accessAddress) {
        this.accessAddress = accessAddress;
    }
}