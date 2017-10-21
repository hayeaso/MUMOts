package com.pm.onlinetest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.onlinetest.domain.DataLogLines;
import com.pm.onlinetest.repository.DataLogLineRepository;
import com.pm.onlinetest.service.DataLogLineService;

@Service
public class DataLogLineServiceImpl implements DataLogLineService {

	
	@Autowired
	private DataLogLineRepository dataLogLineRepository;
	@Override
	public void save(DataLogLines d) {
		// TODO Auto-generated method stub
		dataLogLineRepository.save(d);
	}

	@Override
	public List<DataLogLines> findAll() {
		
		return (List<DataLogLines>) dataLogLineRepository.findAll();
	}

	@Override
	public DataLogLines findById(Integer id) {
		// TODO Auto-generated method stub
		return dataLogLineRepository.findOne(id);
	}

}
