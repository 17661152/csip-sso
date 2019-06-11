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
 * 数据权限值
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@ApiModel(description = "数据权限值信息")
@TableName("TIA_DATA_PERM_VALUE")
public class DataPermValue extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据权限ID")
    @TableField(value = "DATA_PERM_ID")
    private String dataPermId;

    @ApiModelProperty(value = "数据权限值名称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "数据权限值ID")
    @TableId("DATA_PERM_VALUE_ID")
    private String dataPermValueId;

    @ApiModelProperty(value = "条件表达式")
    @TableField("CONDITION_EXPRESSION")
    private String conditionExpression;

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

    @ApiModelProperty(value = "数据权限名称")
    @TableField(exist = false)
    private String dataPermName;

    @ApiModelProperty(value = "排序号码：用于显示顺序")
    @TableField(exist = false)
    private Integer sortNumber;

    @ApiModelProperty(value = "是否启用：0-禁用；1-启用")
    @TableField(exist = false)
    private String isEnable;

    @ApiModelProperty(value = "说明")
    @TableField(exist = false)
    private String descr;

    public String getDataPermId() {
        return dataPermId;
    }

    public void setDataPermId(String dataPermId) {
        this.dataPermId = dataPermId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataPermValueId() {
        return dataPermValueId;
    }

    public void setDataPermValueId(String dataPermValueId) {
        this.dataPermValueId = dataPermValueId;
    }

    public String getConditionExpression() {
        return conditionExpression;
    }

    public void setConditionExpression(String conditionExpression) {
        this.conditionExpression = conditionExpression;
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

    public String getDataPermName() {
        return dataPermName;
    }

    public void setDataPermName(String dataPermName) {
        this.dataPermName = dataPermName;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}
