package com.vince.portal.abs.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.vince.multimodule.facade.CategoryFacade;
import com.vince.multimodule.facade.DemoAppFacade;

public class AbstractController {

	@Autowired
	protected DemoAppFacade demoAppFacade;
	
	@Autowired
	protected CategoryFacade categoryFacade;
}
