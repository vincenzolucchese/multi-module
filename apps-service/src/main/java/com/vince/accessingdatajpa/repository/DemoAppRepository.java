package com.vince.accessingdatajpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.vince.accessingdatajpa.entity.DemoAppModel;

public interface DemoAppRepository extends CrudRepository<DemoAppModel, Long> {

	DemoAppModel findByCode(String code);
}
