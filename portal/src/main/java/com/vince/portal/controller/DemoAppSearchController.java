package com.vince.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.vince.portal.abs.controller.AbstractSearchController;


@Controller
public class DemoAppSearchController extends AbstractSearchController {
	
	private static final String PAGE_RETURN 		= "demoAppSearch";
	
    @GetMapping({"/portfolio/search"})
    public String hello(Model model) {
        model.addAttribute("categoryList", categoryFacade.findAll());
        model.addAttribute("demoAppList", demoAppFacade.search(null));      
       
        return PAGE_RETURN;
    }
}
