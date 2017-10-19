package com.pm.onlinetest.repository;

import org.springframework.data.repository.CrudRepository;

import com.pm.onlinetest.domain.DataLog;

public interface DataLogRepository extends CrudRepository<DataLog, Integer> {

}
