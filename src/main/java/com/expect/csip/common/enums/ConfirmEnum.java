package com.expect.csip.common.enums;

/**
 * 公共枚举. 状态
 *
 * @author jack.zeng
 */
public enum ConfirmEnum {

	UN_CONFIRM("未确认", "1"),

	CONFIRM("已确认", "2");

	/** 描述 */
	private String desc;

	/** 状态 */
	private String status;

	ConfirmEnum(String desc, String status) {
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
