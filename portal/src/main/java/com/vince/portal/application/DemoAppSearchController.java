package com.vince.portal.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vince.multimodule.facade.DemoAppFacade;


@Controller
public class DemoAppSearchController {
	
	private static final String PAGE_RETURN 		= "demoAppSearch";
	
	@Autowired
	private DemoAppFacade demoAppFacade;
	
    @GetMapping({"/portfolio/search"})
    public String hello(Model model,
                        @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        
       
        return PAGE_RETURN;
    }
}
