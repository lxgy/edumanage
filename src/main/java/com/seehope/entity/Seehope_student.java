package com.seehope.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_seehope_student")
public class Seehope_student extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@Column(name = "status")
	private Integer status;
	@Column(name = "name", length = 20)
	private String name;
	@Column(name = "password", length = 50)
	private String password;
	@Column(name = "phone", length = 20)
	private String phone;
	@Column(name = "wechat", length = 20)
	private String wechat;
	@Column(name = "qq", length = 20)
	private String qq;
	@Column(name = "email", length = 40)
	private String email;
	@Column(name = "sex", length = 10)
	private String sex;
	@Column(name = "university", length = 30)
	private String university;
	@Column(name = "major", length = 30)
	private String major;
	@Column(name = "education", length = 20)
	private String education;
	@Column(name = "graduation_time", length = 20)
	private String graduation_time;
	@Column(name = "information", length = 30)
	private String information;
	@Column(name = "IDcard", length = 20)
	private String IDcard;
	@Column(name = "channel", length = 30)
	private String channel;
	@ManyToOne
	@JoinColumn(name = "class_id")
	private Seehope_class seehope_class;
	@Column(name = "graduation_where", length = 40)
	private String graduation_where;
	@Column(name = "contract_time", length = 20)
	private String contract_time;
	@ManyToOne
	@JoinColumn(name = "contract_teacher_id")
	private Seehope_teacher contract_teacher;
	@Column(name = "remark", length = 200)
	private String remark;
	@Column(name = "survey", length = 5)
	private Integer survey;

	public Integer getSurvey() {
		return this.survey;
	}

	public void setSurvey(Integer survey) {
		this.survey = survey;
	}

	public static long getSerialversionuid() {
		return 1L;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWechat() {
		return this.wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
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

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUniversity() {
		return this.university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getGraduation_time() {
		return this.graduation_time;
	}

	public void setGraduation_time(String graduation_time) {
		this.graduation_time = graduation_time;
	}

	public String getInformation() {
		return this.information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Seehope_class getSeehope_class() {
		return this.seehope_class;
	}

	public void setSeehope_class(Seehope_class seehope_class) {
		this.seehope_class = seehope_class;
	}

	public String getGraduation_where() {
		return this.graduation_where;
	}

	public void setGraduation_where(String graduation_where) {
		this.graduation_where = graduation_where;
	}

	public String getContract_time() {
		return this.contract_time;
	}

	public void setContract_time(String contract_time) {
		this.contract_time = contract_time;
	}

	public Seehope_teacher getContract_teacher() {
		return this.contract_teacher;
	}

	public void setContract_teacher(Seehope_teacher contract_teacher) {
		this.contract_teacher = contract_teacher;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIDcard() {
		return this.IDcard;
	}

	public void setIDcard(String iDcard) {
		this.IDcard = iDcard;
	}
}
