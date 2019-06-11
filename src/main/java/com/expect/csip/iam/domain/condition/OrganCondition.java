package com.expect.csip.iam.domain.condition;

import com.expect.common.domain.condition.BaseCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * <p>
 * 组织机构查询条件
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@ApiModel(description = "组织机构查询条件")
public class OrganCondition extends BaseCondition {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "组织名称")
    private String name;

    @ApiModelProperty(value = "父组织ID")
    private String parentOrganId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentOrganId() {
        return parentOrganId;
    }

    public void setParentOrganId(String parentOrganId) {
        this.parentOrganId = parentOrganId;
    }
}
