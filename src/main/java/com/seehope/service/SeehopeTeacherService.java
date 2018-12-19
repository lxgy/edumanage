package com.seehope.service;

import com.seehope.core.page.Page;
import com.seehope.core.page.PageRequest;
import com.seehope.dao.SeehopeTeacherDao;
import com.seehope.dao.core.QueryWhere;
import com.seehope.entity.Seehope_teacher;
import com.seehope.util.date.DateTimeUtil;
import com.seehope.util.security.MD5MixUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SeehopeTeacherService {
	@Autowired
	SeehopeTeacherDao teacherDao;

	public void initTeacher() {
		Seehope_teacher teacher = (Seehope_teacher) this.teacherDao.getBy("phone", "admin");
		if (teacher != null) {
			return;
		}
		teacher = new Seehope_teacher();
		teacher.setPhone("admin");
		teacher.setPassword(MD5MixUtil.md5Mix("123456"));
		teacher.setType(Integer.valueOf(2));
		teacher.setStatus(Integer.valueOf(1));
		teacher.setCreate_time(DateTimeUtil.getCurDateTime());
		teacher.setUpdate_time(DateTimeUtil.getCurDateTime());
		this.teacherDao.save(teacher);
	}

	public void saveTeacher(Seehope_teacher teacher) {
		String now = DateTimeUtil.getCurDateTime();
		teacher.setUpdate_time(now);
		if (teacher.getId() == null) {
			teacher.setCreate_time(now);
			teacher.setStatus(Integer.valueOf(1));
			teacher.setPassword(MD5MixUtil.md5Mix("123456"));
			this.teacherDao.save(teacher);
		} else {
			this.teacherDao.update(teacher);
		}
	}

	public Page<Seehope_teacher> findTeachers(PageRequest pageRequest) {
		return this.teacherDao.findAll(pageRequest);
	}

	public Page<Seehope_teacher> findTeachers(PageRequest pageRequest, String key, Integer type, Boolean status) {
		Map<String, Object> by = new HashMap();
		if ((type != null) && (type.intValue() > 0)) {
			by.put("type", type);
		}
		if ((status == null) || (!status.booleanValue())) {
			by.put("status", Integer.valueOf(1));
		}
		if (key == null) {
			key = "";
		}
		return this.teacherDao.findByAndLike(by, "name", key, pageRequest);
	}

	public List<Seehope_teacher> findTeachersByType(Integer type) {
		Map<String, Object> by = new HashMap();
		if (type != null) {
			by.put("type", type);
		}
		by.put("status", Integer.valueOf(1));
		return this.teacherDao.findBy(by);
	}

	public List<Seehope_teacher> findTeachersNotType(Integer... types) {
		QueryWhere where = new QueryWhere();
		where.and("status", Integer.valueOf(1));
		if (types != null) {
			where.notIn(true, "type", types);
		}
		return this.teacherDao.findByWhere(where);
	}

	public Seehope_teacher getTeacherById(Integer id) {
		return (Seehope_teacher) this.teacherDao.getById(id);
	}

	public List<Seehope_teacher> findAllTeachers() {
		return this.teacherDao.findBy("status", Integer.valueOf(1));
	}

	public Seehope_teacher getTeacherByLoginName(String loginName) {
		QueryWhere where = new QueryWhere();
		where.or("phone", loginName);
		where.or("email", loginName);
		where.and("status", Integer.valueOf(1));
		return (Seehope_teacher) this.teacherDao.getByWhere(where);
	}

	public Seehope_teacher getTeacherByPhone(String phone) {
		return (Seehope_teacher) this.teacherDao.getBy("phone", phone);
	}
}
