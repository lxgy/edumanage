package com.seehope.service;

import com.seehope.core.global.Global;
import com.seehope.core.page.Page;
import com.seehope.core.page.PageRequest;
import com.seehope.dao.SeehopeClassDao;
import com.seehope.dao.SeehopeStudentClassLogDao;
import com.seehope.dao.SeehopeStudentDao;
import com.seehope.dao.core.QueryWhere;
import com.seehope.entity.Seehope_class;
import com.seehope.entity.Seehope_student;
import com.seehope.entity.Seehope_student_class_log;
import com.seehope.entity.Seehope_teacher;
import com.seehope.util.StringUtil;
import com.seehope.util.date.DateTimeUtil;
import com.seehope.util.poi.ExcelPoiUtil;
import com.seehope.util.security.MD5MixUtil;
import com.seehope.web.vo.PageResult;
import com.seehope.web.vo.ResultVo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SeehopeStudentService {
	@Autowired
	SeehopeStudentDao studentDao;
	@Autowired
	SeehopeStudentClassLogDao logDao;
	@Autowired
	SeehopeClassDao classDao;

	public void saveStudent(Seehope_student student) {
		String now = DateTimeUtil.getCurDateTime();
		student.setUpdate_time(now);
		if ((student.getSeehope_class() != null) && (student.getSeehope_class().getId() == null)) {
			student.setSeehope_class(null);

			student.setStatus(Integer.valueOf(10));
		} else {
			student.setStatus(Integer.valueOf(2));
		}
		if ((student.getContract_teacher() != null) && (student.getContract_teacher().getId() == null)) {
			student.setContract_teacher(null);
		}
		if (student.getId() == null) {
			student.setCreate_time(now);
			student.setPassword(MD5MixUtil.md5Mix("123456"));
			this.studentDao.save(student);

			Seehope_student_class_log log = new Seehope_student_class_log();
			log.setCreate_time(now);
			log.setUpdate_time(now);
			log.setSeehope_class(student.getSeehope_class());
			log.setRemark("<br/>��������������");
			if (student.getSeehope_class() != null) {
				log.setRemark(log.getRemark() + "<br/>������"
						+ ((Seehope_class) this.classDao.getById(student.getSeehope_class().getId())).getName());
			}
			log.setStudent(student);
			this.logDao.save(log);
		} else {
			this.studentDao.update(student);
		}
	}

	public Page<Seehope_student> findStudents(PageRequest pageRequest, String key, Boolean status) {
		QueryWhere where = new QueryWhere();
		if ((status == null) || (!status.booleanValue())) {
			where.andNotIn("status", new Number[] { Integer.valueOf(0) });
		}
		if ((key != null) && (!key.equals(""))) {
			where.likeAll("name", key);
		}
		return this.studentDao.findByWhere(where, pageRequest);
	}

	public Seehope_student getStudentById(Integer sid) {
		return (Seehope_student) this.studentDao.getById(sid);
	}

	public List<Seehope_student> findStudentsByClassId(Integer cid) {
		QueryWhere where = new QueryWhere();
		where.andNotIn("status", new Number[] { Integer.valueOf(1) });
		where.and("seehope_class.id", cid);
		return this.studentDao.findByWhere(where);
	}

	public void updateStudent(Seehope_student student) {
		Seehope_student saveStudent = (Seehope_student) this.studentDao.getById(student.getId());
		String now = DateTimeUtil.getCurDateTime();
		saveStudent.setUpdate_time(now);

		saveStudent.setName(student.getName());
		saveStudent.setUniversity(student.getUniversity());
		saveStudent.setMajor(student.getMajor());
		saveStudent.setEducation(student.getEducation());
		saveStudent.setGraduation_time(student.getGraduation_time());

		saveStudent.setContract_time(student.getContract_time());
		saveStudent.setContract_teacher(student.getContract_teacher());

		saveStudent.setGraduation_where(student.getGraduation_where());
		saveStudent.setRemark(student.getRemark());

		saveStudent.setEmail(student.getEmail());
		saveStudent.setPhone(student.getPhone());
		saveStudent.setWechat(student.getWechat());
		saveStudent.setQq(student.getQq());
		saveStudent.setSex(student.getSex());
		if (student.getContract_teacher().getId() == null) {
			saveStudent.setContract_teacher(null);
		}
		this.studentDao.update(saveStudent);
	}

	public void importStudentInfo(String filename) throws Exception {
		String path = Global.filePath + "/upload/tmp/" + filename;
		String templatePath = Global.filePath + "/template/template.xml";
		List<Seehope_student> list = ExcelPoiUtil.excelToObjects(path, templatePath, Seehope_student.class);
		for (Seehope_student seehope_student : list) {
			if ((seehope_student.getPhone() != null) && (!StringUtil.isContainChinese(seehope_student.getPhone()))
					&& (this.studentDao.getBy("phone", seehope_student.getPhone()) == null)) {
				saveStudent(seehope_student);
			}
		}
	}

	public Seehope_student getStudentByLoginName(String loginName) {
		QueryWhere where = new QueryWhere();
		where.or("phone", loginName);
		where.or("email", loginName);
		where.andNotIn("status", new Number[] { Integer.valueOf(0) });
		return (Seehope_student) this.studentDao.getByWhere(where);
	}

	public Seehope_student getStudentByPhone(String phone) {
		return (Seehope_student) this.studentDao.getBy("phone", phone);
	}

	public void updateStudentsClass(Integer[] ids, Integer cid) {
		Seehope_class seehope_class = (Seehope_class) this.classDao.getById(cid);
		String now = DateTimeUtil.getCurDateTime();
		Integer[] arrayOfInteger;
		int j = (arrayOfInteger = ids).length;
		for (int i = 0; i < j; i++) {
			Integer id = arrayOfInteger[i];
			Seehope_student student = (Seehope_student) this.studentDao.getById(id);
			if ((student.getSeehope_class() == null) || ((student.getSeehope_class() != null)
					&& (student.getSeehope_class().getId() != seehope_class.getId()))) {
				student.setUpdate_time(now);
				student.setSeehope_class(seehope_class);
				student.setStatus(Integer.valueOf(2));
				this.studentDao.save(student);

				Seehope_student_class_log log = new Seehope_student_class_log();
				log.setCreate_time(now);
				log.setUpdate_time(now);
				log.setSeehope_class(seehope_class);
				log.setRemark("<br/>����������" + seehope_class.getName());
				log.setStudent(student);
				this.logDao.save(log);
			}
		}
	}

	public void updateStudentStatus(Integer sid, Integer status) {
		Seehope_student student = getStudentById(sid);
		if ((status.intValue() == 3) || (status.intValue() == 4)) {
			student.setSeehope_class(null);
		}
		student.setUpdate_time(DateTimeUtil.getCurDateTime());
		student.setStatus(status);
		this.studentDao.update(student);
	}

	public PageResult<Seehope_student> findGraduatedStudents(PageRequest pageRequest, String key, Integer status) {
		PageResult<Seehope_student> pageResult = new PageResult(Integer.valueOf(200), "��������");
		QueryWhere where = new QueryWhere();
		where.and("status", Integer.valueOf(1));
		if ((key != null) && (!key.equals(""))) {
			where.likeAll("name", key);
		}
		Page<Seehope_student> page = this.studentDao.findByWhere(where, pageRequest);
		pageResult.setPage(page);
		return pageResult;
	}

	public ResultVo isSurvey(Integer id) {
		ResultVo resultVo = new ResultVo("500", "��������");
		try {
			Seehope_student seehope_student = (Seehope_student) this.studentDao.getById(id);
			if ((seehope_student.getSurvey() != null) && (seehope_student.getSurvey().intValue() == 1)) {
				resultVo.setCode("200");
				resultVo.setMsg("��������");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVo;
	}
}
