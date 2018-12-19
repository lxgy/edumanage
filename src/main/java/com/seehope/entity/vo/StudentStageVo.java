package com.seehope.entity.vo;

import com.seehope.entity.Stage_content_student;
import java.util.List;

public class StudentStageVo {
	private Integer id;
	private Integer status;
	private String name;
	private Integer type;
	private List<Stage_content_student> student_evaluation;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<Stage_content_student> getStudent_evaluation() {
		return this.student_evaluation;
	}

	public void setStudent_evaluation(List<Stage_content_student> student_evaluation) {
		this.student_evaluation = student_evaluation;
	}
}
