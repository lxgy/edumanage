package com.seehope.web.vo;

import com.seehope.core.global.CodeEnum;

public class ResultVo {
	private String code = CodeEnum._200.getCode();
	private String msg = "";
	private Object data;

	public ResultVo() {
	}

	public ResultVo(CodeEnum codeenum) {
		this.code = codeenum.getCode();
		this.msg = codeenum.getMsg();
	}

	public ResultVo(CodeEnum codeenum, String msg) {
		this.code = codeenum.getCode();
		this.msg = msg;
	}

	public ResultVo(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return this.data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
