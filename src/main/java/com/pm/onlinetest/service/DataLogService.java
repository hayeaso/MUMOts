package com.pm.onlinetest.service;

import java.util.List;

import com.pm.onlinetest.domain.DataLog;

public interface DataLogService {
	
	public void save(DataLog d);
	public List<DataLog> findAll();
	public DataLog findById(Integer id);

}
