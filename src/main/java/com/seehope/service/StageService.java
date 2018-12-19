package com.seehope.service;

import com.seehope.core.page.Page;
import com.seehope.core.page.PageRequest;
import com.seehope.dao.StageContentDao;
import com.seehope.dao.StageDao;
import com.seehope.dao.core.QueryWhere;
import com.seehope.entity.Stage;
import com.seehope.entity.Stage_content;
import com.seehope.util.date.DateTimeUtil;
import com.seehope.web.vo.PageResult;
import com.seehope.web.vo.ResultVo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StageService {
	@Autowired
	private StageDao stageDao;
	@Autowired
	private StageContentDao contentDao;

	public PageResult<Stage> findStages(PageRequest pageRequest, String keyText, Integer type, Integer status) {
		PageResult<Stage> pageResult = new PageResult(Integer.valueOf(200), "��������");
		QueryWhere queryWhere = new QueryWhere();
		try {
			if (status != null) {
				queryWhere.and("status", status);
			}
			if (type != null) {
				queryWhere.and("type", type);
			}
			if ((keyText != null) && (!keyText.equals(""))) {
				queryWhere.andLikeAll("name", keyText);
			}
			Page<Stage> page = this.stageDao.findByWhere(queryWhere, pageRequest);
			pageResult.setPage(page);

			Map<String, Object> by = new HashMap();
			if (status != null) {
				by.put("status", status);
			}
			if (pageResult.getData() != null) {
				for (Stage stage : pageResult.getData()) {
					by.put("stage.id", stage.getId());
					stage.setContents(this.contentDao.findBy(by));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			pageResult.setCode(Integer.valueOf(500));
			pageResult.setMsg("��������");
		}
		return pageResult;
	}

	public void updateStageStatus(Integer id, Integer status) {
		String time = DateTimeUtil.getCurDateTime();
		Stage stage = (Stage) this.stageDao.getById(id);
		stage.setStatus(status);
		stage.setUpdate_time(time);
		this.stageDao.update(stage);

		List<Stage_content> contents = this.contentDao.findBy("stage.id", id);
		for (Stage_content stage_content : contents) {
			stage_content.setStatus(status);
			stage_content.setUpdate_time(time);
			this.contentDao.update(stage_content);
		}
	}

	public ResultVo updateStage(Stage stage) {
		ResultVo resultVo = new ResultVo("200", "��������");
		try {
			String updateDate = DateTimeUtil.getCurDateTime();
			Stage saveStage = (Stage) this.stageDao.getById(stage.getId());
			saveStage.setName(stage.getName());
			saveStage.setUpdate_time(updateDate);
			this.stageDao.update(saveStage);
		} catch (Exception e) {
			resultVo.setCode("500");
			resultVo.setMsg("��������");
		}
		return resultVo;
	}

	public ResultVo addStage(Stage stage) {
		ResultVo resultVo = new ResultVo("200", "��������");
		try {
			saveStage(stage);
		} catch (Exception e) {
			resultVo.setCode("500");
			resultVo.setMsg("��������");
		}
		return resultVo;
	}

	public void saveStage(Stage stage) {
		String now = DateTimeUtil.getCurDateTime();
		stage.setUpdate_time(now);
		if (stage.getId() == null) {
			stage.setCreate_time(now);
			stage.setStatus(Integer.valueOf(1));
			this.stageDao.save(stage);
		} else {
			this.stageDao.update(stage);
		}
	}

	public List<Stage> findStageByStatusAndType(Integer status, Integer type) {
		Map<String, Object> by = new HashMap();
		if (status != null) {
			by.put("status", status);
		}
		if (type != null) {
			by.put("type", type);
		}
		return this.stageDao.findBy(by);
	}

	public Stage getStageById(Integer id) {
		return (Stage) this.stageDao.getById(id);
	}
}
