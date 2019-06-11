package com.expect.csip.common.enums;

/**
 * 公共枚举. 状态
 *
 * @author jack.zeng
 */
public enum CheckEnum {

	UN_CHECK("未审核", "1"),

	CHECK("已审核", "2");

	/** 描述 */
	private String desc;

	/** 状态 */
	private String status;

	CheckEnum(String desc, String status) {
		this.desc = desc;
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
