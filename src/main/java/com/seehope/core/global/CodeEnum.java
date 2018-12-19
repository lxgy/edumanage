package com.seehope.core.global;

public enum CodeEnum {
	_200("����"), _400("����������"), _404("����������"), _500("����������"), _900("������");

	private final String msg;

	private CodeEnum(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return super.toString().replace("_", "");
	}

	public String getMsg() {
		return this.msg;
	}
}
