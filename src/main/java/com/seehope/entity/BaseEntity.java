package com.seehope.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "create_time", length = 20)
	private String create_time;
	@Column(name = "update_time", length = 20)
	private String update_time;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreate_time() {
		return this.create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getUpdate_time() {
		return this.update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public static long getSerialversionuid() {
		return 1L;
	}
}
