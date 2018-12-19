package com.seehope.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_stage_work")
public class Stage_work extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "stage_id")
	private Stage stage;
	@Column(name = "content", length = 200)
	private String content;

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Stage getStage() {
		return this.stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
