package com.pm.onlinetest.repository;

import org.springframework.data.repository.CrudRepository;

import com.pm.onlinetest.domain.DataLogLines;

public interface DataLogLineRepository extends CrudRepository<DataLogLines, Integer> {

}
