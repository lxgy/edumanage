package com.seehope.service;

import com.seehope.dao.StageQuestionOptionDao;
import com.seehope.entity.Stage_question;
import com.seehope.entity.Stage_question_option;
import com.seehope.util.date.DateTimeUtil;
import com.seehope.web.vo.ResultVo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StageQuestionOptionService {
	@Autowired
	private StageQuestionOptionDao optionDao;

	public ResultVo deleteOption(Integer id) {
		ResultVo resultVo = new ResultVo("200", "��������");
		try {
			this.optionDao.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			resultVo.setCode("500");
			resultVo.setMsg("��������");
		}
		return resultVo;
	}

	public ResultVo saveOption(Stage_question_option option) {
		ResultVo resultVo = new ResultVo("200", "��������");
		try {
			if ((option.getStage_question().getType().intValue() == 1) && (option.getIs_answer() != null)) {
				List<Stage_question_option> options = this.optionDao.findBy("stage_question.id",
						option.getStage_question().getId());
				if (options != null) {
					for (Stage_question_option stage_question_option : options) {
						if ((stage_question_option.getIs_answer() != null)
								&& (stage_question_option.getIs_answer().intValue() == 1)) {
							return new ResultVo("400", "��������������������");
						}
					}
				}
			}
			if (option.getIs_answer() == null) {
				option.setIs_answer(Integer.valueOf(0));
			}
			String now = DateTimeUtil.getCurDateTime();
			option.setUpdate_time(now);
			if (option.getId() == null) {
				option.setCreate_time(now);
				this.optionDao.save(option);
			} else {
				this.optionDao.update(option);
			}
			resultVo.setData(option);
		} catch (Exception e) {
			e.printStackTrace();
			resultVo = new ResultVo("500", "��������");
		}
		return resultVo;
	}

	public ResultVo updateOptionSummary(Stage_question_option option) {
		ResultVo resultVo = new ResultVo("200", "��������");
		try {
			Stage_question_option saveOption = (Stage_question_option) this.optionDao.getById(option.getId());
			saveOption.setUpdate_time(DateTimeUtil.getCurDateTime());
			saveOption.setSummary(option.getSummary());
			this.optionDao.update(saveOption);
			resultVo.setData(saveOption);
		} catch (Exception e) {
			e.printStackTrace();
			resultVo.setCode("500");
			resultVo.setMsg("��������");
		}
		return resultVo;
	}

	public ResultVo updateOptionIsAnswer(Stage_question_option option) {
		ResultVo resultVo = new ResultVo("200", "��������");
		try {
			Stage_question_option saveOption = (Stage_question_option) this.optionDao.getById(option.getId());
			if ((saveOption.getStage_question().getType().intValue() == 1) && (option.getIs_answer().intValue() == 1)) {
				List<Stage_question_option> options = this.optionDao.findBy("stage_question.id",
						saveOption.getStage_question().getId());
				for (Stage_question_option stage_question_option : options) {
					if ((stage_question_option.getIs_answer() != null)
							&& (stage_question_option.getIs_answer().intValue() == 1)
							&& (stage_question_option.getId() != saveOption.getId())) {
						return new ResultVo("400", "��������������������");
					}
				}
			}
			saveOption.setUpdate_time(DateTimeUtil.getCurDateTime());
			saveOption.setIs_answer(option.getIs_answer());
			this.optionDao.update(saveOption);
			resultVo.setData(saveOption);
		} catch (Exception e) {
			e.printStackTrace();
			resultVo.setCode("500");
			resultVo.setMsg("��������");
		}
		return resultVo;
	}
}
