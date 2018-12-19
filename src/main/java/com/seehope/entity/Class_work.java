package com.seehope.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_class_work")
public class Class_work extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@Column(name = "status")
	private Integer status;
	@ManyToOne
	@JoinColumn(name = "class_id")
	private Seehope_class seehope_class;
	@Column(name = "point", length = 50)
	private String point;
	@Column(name = "content", length = 1000)
	private String content;

	public Seehope_class getSeehope_class() {
		return this.seehope_class;
	}

	public void setSeehope_class(Seehope_class seehope_class) {
		this.seehope_class = seehope_class;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPoint() {
		return this.point;
	}

	public void setPoint(String point) {
		this.point = point;
	}
}
