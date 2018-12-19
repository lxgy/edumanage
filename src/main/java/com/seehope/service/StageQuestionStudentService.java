package com.seehope.service;

import com.seehope.dao.StageQuestionStudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StageQuestionStudentService {
	@Autowired
	StageQuestionStudentDao questionStudentDao;
}
