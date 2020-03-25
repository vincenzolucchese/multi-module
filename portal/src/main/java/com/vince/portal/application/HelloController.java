package com.vince.portal.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vince.multimodule.facade.DemoAppFacade;


@Controller
public class HelloController {
	
	private static final String PAGE_RETURN 		= "index";
	private static final String PAGE_RETURN_HELLO 	= "hello";
	
	@Autowired
	private DemoAppFacade demoAppFacade;
	
    @GetMapping({"/", "/hello"})
    public String hello(Model model,
                        @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        
        System.out.println(demoAppFacade.save(null));
        
        return PAGE_RETURN;
    }
}
