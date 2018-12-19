package com.seehope.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "t_survey")
public class Survey extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@Column(name = "status")
	private Integer status;
	@Column(name = "type")
	private Integer type;
	@Column(name = "description", length = 500)
	private String description;
	@Column(name = "remarks", length = 500)
	private String remarks;
	@Transient
	private List<Survey_option> survey_options;

	public List<Survey_option> getSurvey_options() {
		return this.survey_options;
	}

	public void setSurvey_options(List<Survey_option> survey_options) {
		this.survey_options = survey_options;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public static long getSerialversionuid() {
		return 1L;
	}

	public String toString() {
		return "Survey [status=" + this.status + ", type=" + this.type + ", description=" + this.description
				+ ", remarks=" + this.remarks + ", survey_options=" + this.survey_options + "]";
	}
}
