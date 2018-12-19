package com.seehope.web.vo;

import com.seehope.core.page.Page;
import java.util.List;

public class PageResult<T> {
	private Integer code;
	private String msg;
	private Integer count;
	private List<T> data;

	public PageResult(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public void setPage(Page<T> page) {
		this.data = page.results;
		this.count = page.total;
	}

	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<T> getData() {
		return this.data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
