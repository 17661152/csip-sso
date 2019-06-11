package com.expect.csip.basic.domain.condition;

import com.expect.common.domain.condition.BaseCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * <p>
 * 数据字典查询条件
 * </p>
 * @author jack.zeng
 * @since 2019-04-08 22:55:14
 */
@ApiModel(description="数据字典查询条件")
public class DictionaryCondition extends BaseCondition {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典类别")
    private String category;

    @ApiModelProperty(value = "字典名称")
    private String name;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
