package com.vince.portal.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vince.multimodule.data.DemoAppData;
import com.vince.multimodule.facade.DemoAppFacade;


@Controller
public class DemoAppDetailController {
	
	private static final String PAGE_RETURN 		= "demoAppDetail";
	
	@Autowired
	private DemoAppFacade demoAppFacade;
	
    @GetMapping({"/portfolio/detail"})
    public String hello(Model model,
                        @RequestParam(value="code", required=true, defaultValue="World") String name) {
        model.addAttribute("name", name);
        
       
        return PAGE_RETURN;
    }
    
    @GetMapping({"/portfolio/new"})
    public String createGet(Model model,
                        @RequestParam(value="code", required=false, defaultValue="World") String name) {
        model.addAttribute("demoApp", new DemoAppData());
        
       
        return PAGE_RETURN;
    }
    
    @PostMapping({"/portfolio/new"})
    public String createPost(Model model,
                        @RequestParam(value="code", required=false, defaultValue="World") String name) {
        model.addAttribute("demoApp", new DemoAppData());
        
       
        return PAGE_RETURN;
    }
}
