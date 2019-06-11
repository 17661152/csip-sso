package com.expect.csip.iam.domain;

import com.expect.common.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 功能权限服务关系
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@ApiModel(description = "功能权限服务关系信息")
@TableName("TIA_FUNC_PERM_SVC_REL")
public class FuncPermSvcRel extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "服务ID")
    @TableId("SERVICE_ID")
    private String serviceId;

    @ApiModelProperty(value = "功能权限ID")
    @TableField("FUNC_PERM_ID")
    private String funcPermId;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
    public String getFuncPermId() {
        return funcPermId;
    }

    public void setFuncPermId(String funcPermId) {
        this.funcPermId = funcPermId;
    }

}
