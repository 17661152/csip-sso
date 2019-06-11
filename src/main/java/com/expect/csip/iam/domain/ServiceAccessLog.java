package com.expect.csip.iam.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.expect.common.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * <p>
 * 服务访问日志
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@ApiModel(description = "服务访问日志信息")
@TableName("TIA_SERVICE_ACCESS_LOG")
public class ServiceAccessLog extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "服务访问日志ID")
    @TableId("LOG_ID")
    private String logId;

    @ApiModelProperty(value = "用户ID")
    @TableField("USER_ID")
    private String userId;

    @ApiModelProperty(value = "服务ID")
    @TableField("SERVICE_ID")
    private String serviceId;

    @ApiModelProperty(value = "目标参数")
    @TableField("TARGET_PARAM")
    private String targetParam;

    @ApiModelProperty(value = "访问时间")
    @TableField("ACCESS_TIME")
    private Date accessTime;

    @ApiModelProperty(value = "目标地址")
    @TableField("TARGET_ADDRESS")
    private String targetAddress;

    @ApiModelProperty(value = "来源IP")
    @TableField("SOURCE_IP")
    private String sourceIp;

    @ApiModelProperty(value = "浏览器")
    @TableField("BROWSER")
    private String browser;

    @ApiModelProperty(value = "浏览器版本号")
    @TableField("BROWSER_VERSION")
    private String browserVersion;

    @ApiModelProperty(value = "操作系统")
    @TableField("OS")
    private String os;

    @ApiModelProperty(value = "平台")
    @TableField("PLATFORM")
    private String platform;

    @ApiModelProperty(value = "登录名")
    @TableField(exist = false)
    private String loginName;

    @ApiModelProperty(value = "姓名")
    @TableField(exist = false)
    private String userName;

    @ApiModelProperty(value = "国家")
    @TableField(exist = false)
    private String country;

    @ApiModelProperty(value = "省")
    @TableField(exist = false)
    private String province;

    @ApiModelProperty(value = "市")
    @TableField(exist = false)
    private String city;

    @ApiModelProperty(value = "运营商")
    @TableField(exist = false)
    private String isp;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getTargetParam() {
        return targetParam;
    }

    public void setTargetParam(String targetParam) {
        this.targetParam = targetParam;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    public String getTargetAddress() {
        return targetAddress;
    }

    public void setTargetAddress(String targetAddress) {
        this.targetAddress = targetAddress;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }
}
