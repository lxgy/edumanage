package com.seehope.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_seehope_class")
public class Seehope_class extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@Column(name = "status")
	private Integer status;
	@Column(name = "stage", length = 40)
	private String stage;
	@Column(name = "open_time", length = 20)
	private String open_time;
	@Column(name = "name", length = 20)
	private String name;
	@ManyToOne
	@JoinColumn(name = "charge_teacher_id")
	private Seehope_teacher charge_teacher;
	@ManyToOne
	@JoinColumn(name = "teach_teacher_id")
	private Seehope_teacher teach_teacher;
	@Column(name = "visible")
	private Integer visible;

	public Integer getVisible() {
		return this.visible;
	}

	public void setVisible(Integer visible) {
		this.visible = visible;
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

	public String getOpen_time() {
		return this.open_time;
	}

	public void setOpen_time(String open_time) {
		this.open_time = open_time;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Seehope_teacher getCharge_teacher() {
		return this.charge_teacher;
	}

	public void setCharge_teacher(Seehope_teacher charge_teacher) {
		this.charge_teacher = charge_teacher;
	}

	public Seehope_teacher getTeach_teacher() {
		return this.teach_teacher;
	}

	public void setTeach_teacher(Seehope_teacher teach_teacher) {
		this.teach_teacher = teach_teacher;
	}

	public String getStage() {
		return this.stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getStatusName() {
		switch (this.status.intValue()) {
		case 1:
			return "预开班";
		case 2:
			return "已开班";
		case 3:
			return "已毕业";
		}
		return "";
	}
}
