package com.vince.accessingdatajpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.vince.accessingdatajpa.entity.DemoApp;

public interface DemoAppRepository extends CrudRepository<DemoApp, Long> {

}
