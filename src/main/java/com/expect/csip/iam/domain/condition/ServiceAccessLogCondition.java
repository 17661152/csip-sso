package com.expect.csip.iam.domain.condition;

import com.expect.common.domain.condition.BaseCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * <p>
 * 服务访问日志查询条件
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@ApiModel(description = "服务访问日志查询条件")
public class ServiceAccessLogCondition extends BaseCondition {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "登录名")
    private String loginName;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "访问地址")
    private String targetAddress;

    @ApiModelProperty(value = "客户端")
    private String serviceId;

    @ApiModelProperty(value = "开始时间")
    private String startAccessTime;

    @ApiModelProperty(value = "结束时间")
    private String endAccessTime;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTargetAddress() {
        return targetAddress;
    }

    public void setTargetAddress(String targetAddress) {
        this.targetAddress = targetAddress;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getStartAccessTime() {
        return startAccessTime;
    }

    public void setStartAccessTime(String startAccessTime) {
        this.startAccessTime = startAccessTime;
    }

    public String getEndAccessTime() {
        return endAccessTime;
    }

    public void setEndAccessTime(String endAccessTime) {
        this.endAccessTime = endAccessTime;
    }
}
