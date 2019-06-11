package com.expect.csip.iam.domain.condition;

import com.expect.common.domain.condition.BaseCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * <p>
 * 服务查询条件
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@ApiModel(description = "服务查询条件")
public class ServiceCondition extends BaseCondition {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "服务名")
    private String serviceName;

    @ApiModelProperty(value = "访问地址")
    private String accessAddress;

    @ApiModelProperty(value = "是否记录访问日志：0-不记录；1-记录")
    private String isRecordAccessLog;

    @ApiModelProperty(value = "是否访问白名单：0-否；1-是")
    private String isWhite;

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

    public String getIsRecordAccessLog() {
        return isRecordAccessLog;
    }

    public void setIsRecordAccessLog(String isRecordAccessLog) {
        this.isRecordAccessLog = isRecordAccessLog;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getIsWhite() {
        return isWhite;
    }

    public void setIsWhite(String isWhite) {
        this.isWhite = isWhite;
    }
}
