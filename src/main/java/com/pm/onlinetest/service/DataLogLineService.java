package com.pm.onlinetest.service;

import java.util.List;

import com.pm.onlinetest.domain.DataLogLines;;

public interface DataLogLineService {
	public void save(DataLogLines d);
	public List<DataLogLines> findAll();
	public DataLogLines findById(Integer id);
}
