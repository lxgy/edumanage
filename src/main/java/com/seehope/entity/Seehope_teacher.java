package com.seehope.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_seehope_teacher")
public class Seehope_teacher extends BaseEntity {
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
	@Column(name = "type")
	private Integer type;

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

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
