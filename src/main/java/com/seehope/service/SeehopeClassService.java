package com.seehope.service;

import com.seehope.core.page.Page;
import com.seehope.core.page.PageRequest;
import com.seehope.dao.SeehopeClassDao;
import com.seehope.dao.SeehopeClassLogDao;
import com.seehope.dao.SeehopeStudentClassLogDao;
import com.seehope.dao.SeehopeStudentDao;
import com.seehope.dao.SeehopeTeacherDao;
import com.seehope.dao.core.QueryWhere;
import com.seehope.entity.Seehope_class;
import com.seehope.entity.Seehope_class_log;
import com.seehope.entity.Seehope_student;
import com.seehope.entity.Seehope_student_class_log;
import com.seehope.entity.Seehope_teacher;
import com.seehope.util.date.DateTimeUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SeehopeClassService {
	@Autowired
	SeehopeClassDao classesDao;
	@Autowired
	SeehopeStudentDao studentDao;
	@Autowired
	SeehopeTeacherDao teacherDao;
	@Autowired
	SeehopeClassLogDao logDao;
	@Autowired
	SeehopeStudentClassLogDao studentClassLogDao;

	public void saveClasses(Seehope_class clazz) {
		String now = DateTimeUtil.getCurDateTime();
		clazz.setUpdate_time(now);
		clazz.setCreate_time(now);
		clazz.setVisible(Integer.valueOf(1));

		Seehope_class_log log = new Seehope_class_log();
		log.setCreate_time(now);
		log.setUpdate_time(now);
		log.setSeehope_class(clazz);
		StringBuilder remark = new StringBuilder("<br/>����������" + clazz.getStatusName());
		remark.append("<br/>����������" + clazz.getOpen_time());
		if ((clazz.getCharge_teacher() != null) && (clazz.getCharge_teacher().getId() == null)) {
			clazz.setCharge_teacher(null);
		} else {
			remark.append("<br/>��������"
					+ ((Seehope_teacher) this.teacherDao.getById(clazz.getCharge_teacher().getId())).getName());
		}
		if ((clazz.getTeach_teacher() != null) && (clazz.getTeach_teacher().getId() == null)) {
			clazz.setTeach_teacher(null);
		} else {
			remark.append("<br/>������"
					+ ((Seehope_teacher) this.teacherDao.getById(clazz.getTeach_teacher().getId())).getName());
		}
		log.setRemark(remark.toString());

		this.classesDao.save(clazz);
		this.logDao.save(log);
	}

	public Page<Seehope_class> findClasses(PageRequest pageRequest, String key, Integer status) {
		Map<String, Object> by = new HashMap();
		by.put("visible", Integer.valueOf(1));
		if ((status != null) && (status.intValue() > 0)) {
			by.put("status", status);
		}
		if ((key != null) && (!key.equals(""))) {
			return this.classesDao.findByAndLike(by, "name", key, pageRequest);
		}
		return this.classesDao.findBy(by, pageRequest);
	}

	public List<Seehope_class> findAllClass() {
		return this.classesDao.findAll();
	}

	public List<Seehope_class> findClassByStatus(Integer status) {
		return this.classesDao.findBy("status", status);
	}

	public Seehope_class getClassById(Integer id) {
		return (Seehope_class) this.classesDao.getById(id);
	}

	public void updateClass(Seehope_class clazz) {
		String now = DateTimeUtil.getCurDateTime();

		Seehope_class_log log = new Seehope_class_log();
		log.setCreate_time(now);
		log.setUpdate_time(now);
		log.setSeehope_class(clazz);
		StringBuilder remark = new StringBuilder();

		Seehope_class saveClass = (Seehope_class) this.classesDao.getById(clazz.getId());
		saveClass.setName(clazz.getName());
		saveClass.setOpen_time(clazz.getOpen_time());
		saveClass.setUpdate_time(now);
		if (saveClass.getStatus() != clazz.getStatus()) {
			remark.append("<br/>����������" + clazz.getStatusName());
		}
		saveClass.setStatus(clazz.getStatus());
		if (!saveClass.getStage().equals(clazz.getStage())) {
			remark.append("<br/>����������" + clazz.getStage());

			Map<String, Object> by = new HashMap();
			by.put("status", Integer.valueOf(1));
			by.put("seehope_class.id", clazz.getId());
			List<Seehope_student> list = this.studentDao.findBy(by);
			for (Seehope_student seehope_student : list) {
				Seehope_student_class_log student_class_log = new Seehope_student_class_log();
				student_class_log.setCreate_time(now);
				student_class_log.setUpdate_time(now);
				student_class_log.setStudent(seehope_student);
				student_class_log.setSeehope_class(saveClass);
				student_class_log.setRemark("<br/>������" + clazz.getStage());
				this.studentClassLogDao.save(student_class_log);
			}
		}
		saveClass.setStage(clazz.getStage());
		if (saveClass.getCharge_teacher() == null) {
			if (clazz.getCharge_teacher().getId() != null) {
				remark.append("<br/>��������"
						+ ((Seehope_teacher) this.teacherDao.getById(clazz.getCharge_teacher().getId())).getName());
				saveClass.setCharge_teacher(clazz.getCharge_teacher());
			}
		} else if (clazz.getCharge_teacher().getId() == null) {
			remark.append("<br/>����������");
			saveClass.setCharge_teacher(null);
		} else if (saveClass.getCharge_teacher().getId() != clazz.getCharge_teacher().getId()) {
			remark.append("<br/>��������"
					+ ((Seehope_teacher) this.teacherDao.getById(clazz.getCharge_teacher().getId())).getName());
			saveClass.setCharge_teacher(clazz.getCharge_teacher());
		}
		if (saveClass.getTeach_teacher() == null) {
			if (clazz.getTeach_teacher().getId() != null) {
				remark.append("<br/>��������"
						+ ((Seehope_teacher) this.teacherDao.getById(clazz.getTeach_teacher().getId())).getName());
				saveClass.setTeach_teacher(clazz.getTeach_teacher());
			}
		} else if (clazz.getTeach_teacher().getId() == null) {
			remark.append("<br/>����������");
			saveClass.setTeach_teacher(null);
		} else if (saveClass.getTeach_teacher().getId() != clazz.getTeach_teacher().getId()) {
			remark.append("<br/>��������"
					+ ((Seehope_teacher) this.teacherDao.getById(clazz.getTeach_teacher().getId())).getName());
			saveClass.setTeach_teacher(clazz.getTeach_teacher());
		}
		this.classesDao.update(saveClass);
		if (!"".equals(remark.toString())) {
			log.setRemark(remark.toString());
			this.logDao.save(log);
		}
	}

	public Page<Seehope_class> findSelfClasses(PageRequest pageRequest, Boolean isGraduation, Seehope_teacher teacher) {
		QueryWhere where = new QueryWhere();
		if ((teacher != null) && (teacher.getType().intValue() == 1)) {
			where.and("teach_teacher.id", teacher.getId());
		} else if ((teacher != null) && (teacher.getType().intValue() == 2)) {
			where.and("charge_teacher.id", teacher.getId());
		}
		if ((isGraduation == null) || (!isGraduation.booleanValue())) {
			where.andNotIn("status", new Number[] { Integer.valueOf(3) });
		}
		return this.classesDao.findByWhere(where, pageRequest);
	}

	public void deleteClassById(Integer id) {
		Seehope_class seehope_class = (Seehope_class) this.classesDao.getById(id);
		seehope_class.setVisible(Integer.valueOf(0));
		this.classesDao.update(seehope_class);
	}
}
