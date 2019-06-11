package com.expect.csip.iam.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.expect.common.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 页面
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@ApiModel(description = "页面信息")
@TableName("TIA_PAGE")
public class Page extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "页面ID")
    @TableId(value = "PAGE_ID", type = IdType.INPUT)
    private String pageId;

    @ApiModelProperty(value = "访问地址")
    @TableField("ACCESS_ADDRESS")
    private String accessAddress;

    @ApiModelProperty(value = "是否内部页面，1：是，0：否")
    @TableField("IS_INTERNAL")
    private String isInternal;

    @ApiModelProperty(value = "组件路径，同等于视图页面路径")
    @TableField("COMPONENT")
    private String component;

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

    @ApiModelProperty(value = "功能权限ID")
    @TableField(exist = false)
    private String funcPermId;

    @ApiModelProperty(value = "名称")
    @TableField(exist = false)
    private String name;

    @ApiModelProperty(value = "类型：1-菜单；2-页面；3-元素")
    @TableField(exist = false)
    private String type;

    @ApiModelProperty(value = "是否启用：0-禁用；1-启用；")
    @TableField(exist = false)
    private String isEnable;

    @ApiModelProperty(value = "排序号码：用于显示顺序")
    @TableField(exist = false)
    private Integer sortNumber;

    @ApiModelProperty(value = "说明")
    @TableField(exist = false)
    private String descr;

    @ApiModelProperty(value = "所属菜单ID集合")
    @TableField(exist = false)
    private List<String> menuIds;

    @ApiModelProperty(value = "服务列表")
    @TableField(exist = false)
    private List<Service> services;

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getAccessAddress() {
        return accessAddress;
    }

    public void setAccessAddress(String accessAddress) {
        this.accessAddress = accessAddress;
    }

    public String getIsInternal() {
        return isInternal;
    }

    public void setIsInternal(String isInternal) {
        this.isInternal = isInternal;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
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

    public List<String> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<String> menuIds) {
        this.menuIds = menuIds;
    }

    public String getFuncPermId() {
        return funcPermId;
    }

    public void setFuncPermId(String funcPermId) {
        this.funcPermId = funcPermId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
}
