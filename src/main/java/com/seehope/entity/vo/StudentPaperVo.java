package com.seehope.entity.vo;

public class StudentPaperVo {
	private String[] danswer;
	private String[] sanswer;
	private String[] wanswer;
	private Integer id;

	public String[] getDanswer() {
		return this.danswer;
	}

	public void setDanswer(String[] danswer) {
		this.danswer = danswer;
	}

	public String[] getSanswer() {
		return this.sanswer;
	}

	public void setSanswer(String[] sanswer) {
		this.sanswer = sanswer;
	}

	public String[] getWanswer() {
		return this.wanswer;
	}

	public void setWanswer(String[] wanswer) {
		this.wanswer = wanswer;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
