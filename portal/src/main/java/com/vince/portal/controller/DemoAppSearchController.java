package com.vince.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vince.multimodule.data.DemoAppData;
import com.vince.portal.abs.controller.AbstractSearchController;


@Controller
@RequestMapping("/portfolio")
public class DemoAppSearchController extends AbstractSearchController {
	
	private static final String PAGE_RETURN 		= "demoAppSearch";
	
    @GetMapping({"/search"})
    public String hello(Model model) {
        model.addAttribute("categoryList", categoryFacade.findAll());
        model.addAttribute("demoAppList", demoAppFacade.search(new DemoAppData()));       
        return PAGE_RETURN;
    }
}
