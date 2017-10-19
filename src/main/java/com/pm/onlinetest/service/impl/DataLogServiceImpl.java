package com.pm.onlinetest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.onlinetest.domain.DataLog;
import com.pm.onlinetest.repository.DataLogRepository;
import com.pm.onlinetest.service.DataLogService;

@Service
public class DataLogServiceImpl implements DataLogService {

	@Autowired
	private DataLogRepository dataLogRepository;

	@Override
	public List<DataLog> findAll() {
		return (List<DataLog>) dataLogRepository.findAll();
	}

	@Override
	public DataLog findById(Integer id) {
		return dataLogRepository.findOne(id);
	}

	@Override
	public void save(DataLog d) {
		dataLogRepository.save(d);
		
	}

}
