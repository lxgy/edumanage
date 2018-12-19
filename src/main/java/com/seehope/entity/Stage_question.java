package com.seehope.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "t_stage_question")
public class Stage_question extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@Column(name = "status")
	private Integer status;
	@Column(name = "stage_type")
	private Integer stage_type;
	@Column(name = "question", length = 500)
	private String question;
	@Column(name = "idea", length = 250)
	private String idea;
	@Column(name = "level")
	private Integer level;
	@Column(name = "type")
	private Integer type;
	@Transient
	private List<Stage_question_option> options;

	public Integer getStage_type() {
		return this.stage_type;
	}

	public void setStage_type(Integer stage_type) {
		this.stage_type = stage_type;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Stage_question_option> getOptions() {
		return this.options;
	}

	public void setOptions(List<Stage_question_option> options) {
		this.options = options;
	}

	public String getIdea() {
		return this.idea;
	}

	public void setIdea(String idea) {
		this.idea = idea;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
}
