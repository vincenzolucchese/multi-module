package com.vince.accessingdatajpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vince.accessingdatajpa.repository.DemoAppRepository;
import com.vince.accessingdatajpa.service.DemoAppService;

@Service
public class DemoAppServiceImpl implements DemoAppService {
	
	@Autowired
	private DemoAppRepository repository;

	public DemoAppRepository getRepository() {
		return repository;
	}

}
