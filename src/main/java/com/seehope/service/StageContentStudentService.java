package com.seehope.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seehope.core.global.Const;
import com.seehope.core.global.Global;
import com.seehope.core.page.Page;
import com.seehope.core.page.PageRequest;
import com.seehope.dao.SeehopeStudentDao;
import com.seehope.dao.StageContentDao;
import com.seehope.dao.StageContentStudentDao;
import com.seehope.dao.StageDao;
import com.seehope.dao.core.QueryWhere;
import com.seehope.entity.Evaluation;
import com.seehope.entity.Seehope_student;
import com.seehope.entity.Stage;
import com.seehope.entity.Stage_content;
import com.seehope.entity.Stage_content_student;
import com.seehope.entity.vo.StudentStageVo;
import com.seehope.util.date.DateTimeUtil;
import com.seehope.util.poi.ExcelPoiUtil;

/**
 * 
 * @author zou
 *
 */
@Service
@Transactional
public class StageContentStudentService {

	@Autowired
	private StageContentStudentDao contentStudentDao;

	@Autowired
	private StageContentDao contentDao;

	@Autowired
	private StageDao stageDao;

	@Autowired
	private SeehopeStudentDao studentDao;

	@Autowired
	private EvaluationService evaluationService;

	/**
	 * 根据id获取实体.
	 * 
	 * @author gy
	 * @param id
	 * @return
	 */
	public Stage_content_student getStageStudentById(Integer id) {
		return this.contentStudentDao.getStageStudentById(id);
	}

	/**
	 * 更新
	 * @param content_student
	 */
	public void updateStageContentStudent(Stage_content_student content_student) {
		content_student.setUpdate_time(DateTimeUtil.getCurDateTime());
		contentStudentDao.update(content_student);
	}

	/**
	 *  查询学生 阶段评价
	 * @param pageRequest   分页查询条件
	 * @param sid 			学生id
	 * @param type			阶段类型 1,2,3
	 * @param status        状态 1正常 0删除
	 * @return
	 */
	public Page<Stage> findStudentStageEvaluation(PageRequest pageRequest, Integer sid, Integer type) {
		Seehope_student student = studentDao.getById(sid);

		// 查询阶段
		QueryWhere where = new QueryWhere();
		where.and("status", 1);
		if (type != null) {
			where.and("type", type);
		}
		Page<Stage> page = stageDao.findByWhere(where, pageRequest);

		// 查询学生阶段内容
		Map<String, Object> contentBy = new HashMap<>();
		contentBy.put("status", 1);
		if (page.getResults() != null) {
			for (Stage stage : page.getResults()) {
				stage.setStudent_evaluation(new ArrayList<Stage_content_student>());
				contentBy.put("stage.id", stage.getId());
				for (Stage_content content : contentDao.findBy(contentBy)) {
					stage.getStudent_evaluation().add(contentStudentDao.getByContentAndStudent(content, student));
				}
			}
		}
		return page;
	}

	/**
	 * 获得学生 阶段评价信息
	 * @param sid 			学生id
	 * @param type			阶段类型 1,2,3
	 * @return
	 */
	public List<StudentStageVo> findStudentStageEvaluation(Integer sid, Integer type) {
		Seehope_student student = studentDao.getById(sid);

		// 查询阶段
		QueryWhere where = new QueryWhere();
		where.and("status", 1);
		if (type != null) {
			where.and("type", type);
		}
		List<Stage> list = stageDao.findByWhere(where);

		// 装换beans vo
		List<StudentStageVo> studentStageVos = new ArrayList<>();
		for (Stage stage : list) {
			StudentStageVo studentStageVo = new StudentStageVo();
			BeanUtils.copyProperties(stage, studentStageVo);
			studentStageVos.add(studentStageVo);
		}

		// 查询学生阶段内容
		Map<String, Object> contentBy = new HashMap<>();
		contentBy.put("status", 1);
		for (StudentStageVo studentStageVo : studentStageVos) {
			studentStageVo.setStudent_evaluation(new ArrayList<Stage_content_student>());
			contentBy.put("stage.id", studentStageVo.getId());
			for (Stage_content content : contentDao.findBy(contentBy)) {
				studentStageVo.getStudent_evaluation().add(contentStudentDao.getByContentAndStudent(content, student));
			}
		}
		return studentStageVos;
	}

	/**
	 * 生成学生阶段信息文件
	 * @param sid   学生id
	 * @return    文件路径
	 * @throws Exception 
	 */
	public String createStudentStageFile(Integer sid) throws Exception {

		// 阶段信息
		List<StudentStageVo> type_1 = findStudentStageEvaluation(sid, 1);
		List<StudentStageVo> type_2 = findStudentStageEvaluation(sid, 2);
		List<StudentStageVo> type_3 = findStudentStageEvaluation(sid, 3);
		List<List<StudentStageVo>> lists = new ArrayList<>();
		lists.add(type_1);
		lists.add(type_2);
		lists.add(type_3);

		// 大阶段总评信息
		List<Evaluation> evaluations = evaluationService.findStudentEvaluation(sid);

		String createPath = Global.filePath + Const.UPLOAD_TMP_FOLDER;
		String filename = ExcelPoiUtil.StudentStageToExcel(createPath, lists, evaluations);

		return createPath + filename;
	}

}
