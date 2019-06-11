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
 * 菜单
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@ApiModel(description = "菜单信息")
@TableName("TIA_MENU")
public class Menu extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单ID")
    @TableId(value = "MENU_ID", type = IdType.INPUT)
    private String menuId;

    @ApiModelProperty(value = "菜单编码")
    @TableField("MENU_CODE")
    private String menuCode;

    @ApiModelProperty(value = "父菜单ID")
    @TableField("PARENT_MENU_ID")
    private String parentMenuId;

    @ApiModelProperty(value = "页面ID")
    @TableField("PAGE_ID")
    private String pageId;

    @ApiModelProperty(value = "级别")
    @TableField("TREE_LEVEL")
    private Integer treeLevel;

    @ApiModelProperty(value = "图标地址")
    @TableField("ICON_ADDRESS")
    private String iconAddress;

    @ApiModelProperty(value = "简称路径：从组织根简称到本组织简称的路径，包含本组织简称。")
    @TableField("NAME_PATH")
    private String namePath;

    @ApiModelProperty(value = "ID路径")
    @TableField("ID_PATH")
    private String idPath;

    @ApiModelProperty(value = "编码路径")
    @TableField("CODE_PATH")
    private String codePath;

    @ApiModelProperty(value = "是否叶结点")
    @TableField("IS_LEAF_NODE")
    private String isLeafNode;

    @ApiModelProperty(value = "菜单布局;RouteView(不带面包屑)和PageView(带面包屑)")
    @TableField("LAYOUT")
    private String layout;

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

    @ApiModelProperty(value = "访问地址")
    @TableField(exist = false)
    private String accessAddress;

    @ApiModelProperty(value = "是否内部页面，1：是，0：否")
    @TableField(exist = false)
    private String isInternal;

    @ApiModelProperty(value = "组件路径，同等于视图页面路径")
    @TableField(exist = false)
    private String component;

    @ApiModelProperty(value = "页面名称")
    @TableField(exist = false)
    private String pageName;

    @ApiModelProperty(value = "数据权限集合")
    @TableField(exist = false)
    private List<DataPerm> dataPerms;

    @ApiModelProperty(value = "服务集合")
    @TableField(exist = false)
    private List<Service> services;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public Integer getTreeLevel() {
        return treeLevel;
    }

    public void setTreeLevel(Integer treeLevel) {
        this.treeLevel = treeLevel;
    }

    public String getIconAddress() {
        return iconAddress;
    }

    public void setIconAddress(String iconAddress) {
        this.iconAddress = iconAddress;
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

    public List<DataPerm> getDataPerms() {
        return dataPerms;
    }

    public void setDataPerms(List<DataPerm> dataPerms) {
        this.dataPerms = dataPerms;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public String getNamePath() {
        return namePath;
    }

    public void setNamePath(String namePath) {
        this.namePath = namePath;
    }

    public String getIdPath() {
        return idPath;
    }

    public void setIdPath(String idPath) {
        this.idPath = idPath;
    }

    public String getIsLeafNode() {
        return isLeafNode;
    }

    public void setIsLeafNode(String isLeafNode) {
        this.isLeafNode = isLeafNode;
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

    public String getFuncPermId() {
        return funcPermId;
    }

    public void setFuncPermId(String funcPermId) {
        this.funcPermId = funcPermId;
    }

    public String getCodePath() {
        return codePath;
    }

    public void setCodePath(String codePath) {
        this.codePath = codePath;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }
}
