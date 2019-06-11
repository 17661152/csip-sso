package com.expect.csip.iam.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.expect.common.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * <p>
 * 账号
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@ApiModel(description = "账号信息")
@TableName("TIA_ACCOUNT_NUMBER")
public class AccountNumber extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "登录名")
    @TableId(value = "LOGIN_NAME", type = IdType.INPUT)
    private String loginName;

    @ApiModelProperty(value = "密码")
    @TableField("PASSWORD")
    private String password;

    @ApiModelProperty(value = "状态：0-停用；1-启用；")
    @TableField("STATUS")
    private String status;

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

    @ApiModelProperty(value = "删除标记：0-无效记录（已删除）；1-有效记录（未删除）；")
    @TableField("DELETE_FLAG")
    @TableLogic
    private String deleteFlag;

    @ApiModelProperty(value = "用户")
    @TableField(exist = false)
    private User user;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
