package com.expect.csip.iam.enums;

/**
 * 公共枚举. 状态
 *
 * @author jack.zeng
 */
public enum FuncPermEnum {

	MENU("菜单", "1"),

	PAGE("页面", "2"),

	ELEMENT("元素", "3");

	/** 描述 */
	private String desc;

	/** 状态 */
	private String type;

	FuncPermEnum(String desc, String type) {
		this.desc = desc;
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
