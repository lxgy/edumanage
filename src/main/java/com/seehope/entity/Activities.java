package com.seehope.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 活动报名实体信息.
 * =============
 * @author lxgy
 *
 */
@Entity
@Table(name = "t_activities")
public class Activities extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* 电话号码 */
	@Column(name = "phone")
	private String phone;
	/* 姓名 */
	@Column(name = "name")
	private String name;
	/* qq号码 */
	@Column(name = "qq_num")
	private String qq_num;
	/* 学校名称 */
	@Column(name = "school")
	private String school;
	/* 邮箱 */
	@Column(name = "email")
	private String email;
	/* 毕业时间 */
	@Column(name = "end_time")
	private String end_time;
	/* 系统显示 */
	@Column(name = "visible")
	private Integer visible;

	public Integer getVisible() {
		return visible;
	}

	public void setVisible(Integer visible) {
		this.visible = visible;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQq_num() {
		return qq_num;
	}

	public void setQq_num(String qq_num) {
		this.qq_num = qq_num;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Activities [phone=" + phone + ", name=" + name + ", qq_num=" + qq_num + ", school=" + school
				+ ", email=" + email + ", end_time=" + end_time + "]";
	}

}
