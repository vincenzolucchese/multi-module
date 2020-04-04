package com.vince.accessingdatajpa.service;

import com.vince.accessingdatajpa.entity.DemoAppModel;
import com.vince.accessingdatajpa.repository.DemoAppRepository;

public interface DemoAppService {

	DemoAppModel findByCode(String code);
	DemoAppRepository getRepository();
}
