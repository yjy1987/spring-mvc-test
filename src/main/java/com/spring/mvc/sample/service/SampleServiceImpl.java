package com.spring.mvc.sample.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mvc.sample.dao.SampleDao;

@Service("sampleService")
public class SampleServiceImpl implements SampleService {

	@Autowired
	private SampleDao sampleDao;
	
	@Override
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception {
		return sampleDao.selectBoardList(map);
	}

}
