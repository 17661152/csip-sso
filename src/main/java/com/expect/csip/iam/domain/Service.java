package com.expect.csip.iam.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.expect.common.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * <p>
 * 服务
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@ApiModel(description = "服务信息")
@TableName("TIA_SERVICE")
public class Service extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "服务ID")
    @TableId("SERVICE_ID")
    private String serviceId;

    @ApiModelProperty(value = "名称")
    @TableField(value = "NAME")
    private String name;

    @ApiModelProperty(value = "服务名")
    @TableField("SERVICE_NAME")
    private String serviceName;

    @ApiModelProperty(value = "访问地址：全表唯一，相对URL，不含域名、IP、端口")
    @TableField(value = "ACCESS_ADDRESS")
    private String accessAddress;

    @ApiModelProperty(value = "是否记录访问日志：0-不记录；1-记录")
    @TableField("IS_RECORD_ACCESS_LOG")
    private String isRecordAccessLog;

    @ApiModelProperty(value = "是否访问白名单：0-否；1-是")
    @TableField("IS_WHITE")
    private String isWhite;

    @ApiModelProperty(value = "描述")
    @TableField("DESCR")
    private String descr;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_PERSON", fill = FieldFill.INSERT)
    private String createPerson;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "最后更新人")
    @TableField(value = "LAST_UPDATE_PERSON", fill = FieldFill.INSERT_UPDATE)
    private String lastUpdatePerson;

    @ApiModelProperty(value = "最后更新时间")
    @TableField(value = "LAST_UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date lastUpdateTime;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdatePerson() {
        return lastUpdatePerson;
    }

    public void setLastUpdatePerson(String lastUpdatePerson) {
        this.lastUpdatePerson = lastUpdatePerson;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getIsWhite() {
        return isWhite;
    }

    public void setIsWhite(String isWhite) {
        this.isWhite = isWhite;
    }
}
