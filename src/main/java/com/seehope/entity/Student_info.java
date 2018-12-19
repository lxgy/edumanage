package com.seehope.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_student_info", uniqueConstraints = {
		@javax.persistence.UniqueConstraint(columnNames = { "phone", "qq", "email" }) })
public class Student_info extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@Column(name = "username", nullable = false)
	private String username;
	@Column(name = "phone", nullable = false)
	private String phone;
	@Column(name = "qq")
	private String qq;
	@Column(name = "email")
	private String email;
	@Column(name = "job")
	private String job;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "survey")
	private Integer survey;

	public Integer getSurvey() {
		return this.survey;
	}

	public void setSurvey(Integer survey) {
		this.survey = survey;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
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
		return "Student_info [username=" + this.username + ", phone=" + this.phone + ", qq=" + this.qq + ", email="
				+ this.email + ", job=" + this.job + ", remarks=" + this.remarks + "]";
	}
}
