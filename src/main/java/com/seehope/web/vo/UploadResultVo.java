package com.seehope.web.vo;

import com.seehope.util.json.JsonUtil;

public class UploadResultVo {
	private boolean result;
	private String ori;
	private String name;
	private String url;
	private String msg;

	public UploadResultVo(boolean result, String msg, String name, String url, String ori) {
		this.result = result;
		this.msg = msg;
		this.name = name;
		this.url = url;
		this.ori = ori;
	}

	public boolean isResult() {
		return this.result;
	}

	public String getOri() {
		return this.ori;
	}

	public void setOri(String ori) {
		this.ori = ori;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String toJsonMsg() {
		return JsonUtil.toJson(this);
	}
}
