package com.seehope.service;

import com.seehope.core.page.Page;
import com.seehope.core.page.PageRequest;
import com.seehope.dao.StageContentDao;
import com.seehope.dao.core.QueryWhere;
import com.seehope.entity.Stage_content;
import com.seehope.util.date.DateTimeUtil;
import com.seehope.web.vo.PageResult;
import com.seehope.web.vo.ResultVo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StageContentService {
	@Autowired
	private StageContentDao stageContentDao;

	public PageResult<Stage_content> find_active_content(PageRequest pageRequest, String keyText) {
		PageResult<Stage_content> pageResult = new PageResult(Integer.valueOf(200), "��������");
		QueryWhere queryWhere = new QueryWhere();
		queryWhere.and("status", Integer.valueOf(1));
		try {
			if ((keyText != null) && (!keyText.equals(""))) {
				queryWhere.orLikeAll("content", keyText);
				queryWhere.orLikeAll("stage.name", keyText);
			}
			Page<Stage_content> page = this.stageContentDao.findByWhere(queryWhere, pageRequest);
			pageResult.setPage(page);
		} catch (Exception e) {
			pageResult.setCode(Integer.valueOf(500));
			pageResult.setMsg("��������");
		}
		return pageResult;
	}

	public List<Stage_content> getListStageContent() {
		List<Stage_content> contents = this.stageContentDao.getListStageContent();
		return contents;
	}

	public Stage_content getStageContentById(Integer id) {
		return this.stageContentDao.getStageContentById(id);
	}

	public ResultVo deleteContent(Integer id) {
		ResultVo resultVo = new ResultVo("200", "��������");
		try {
			Stage_content content = (Stage_content) this.stageContentDao.getById(id);
			content.setStatus(Integer.valueOf(0));
			this.stageContentDao.update(content);
		} catch (Exception e) {
			resultVo.setCode("500");
			resultVo.setMsg("��������");
		}
		return resultVo;
	}

	public ResultVo addStageContent(Stage_content content) {
		ResultVo resultVo = new ResultVo("200", "��������");
		try {
			String now = DateTimeUtil.getCurDateTime();
			content.setUpdate_time(now);
			content.setCreate_time(now);
			content.setStatus(Integer.valueOf(1));
			this.stageContentDao.save(content);
		} catch (Exception e) {
			resultVo.setCode("500");
			resultVo.setMsg("��������");
		}
		return resultVo;
	}

	public ResultVo updateContent(Stage_content content) {
		ResultVo resultVo = new ResultVo("200", "��������");
		try {
			String update_time = DateTimeUtil.getCurDateTime();
			Stage_content saveContent = (Stage_content) this.stageContentDao.getById(content.getId());
			saveContent.setContent(content.getContent());
			saveContent.setUpdate_time(update_time);
			this.stageContentDao.update(saveContent);
		} catch (Exception e) {
			resultVo.setCode("500");
			resultVo.setMsg("��������");
		}
		return resultVo;
	}

	public List<Stage_content> findStageContentByStageId(Integer sid) {
		return this.stageContentDao.findBy("stage.id", sid);
	}
}
