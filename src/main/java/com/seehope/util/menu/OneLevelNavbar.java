package com.seehope.util.menu;

import java.util.List;

public class OneLevelNavbar {
	private String id;
	private String title;
	private String icon;
	private String href;
	private Boolean spread;
	private Integer code;
	private List<SecondLevelNavbar> children;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getHref() {
		return this.href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Boolean getSpread() {
		return this.spread;
	}

	public void setSpread(Boolean spread) {
		this.spread = spread;
	}

	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public List<SecondLevelNavbar> getChildren() {
		return this.children;
	}

	public void setChildren(List<SecondLevelNavbar> children) {
		this.children = children;
	}
}
