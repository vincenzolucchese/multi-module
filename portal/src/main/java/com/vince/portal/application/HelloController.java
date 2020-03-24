package com.vince.portal.application;

import javax.annotation.Resource;

import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vince.accessingdatajpa.service.CustomerService;

@Controller
public class HelloController {
	
	private static final String PAGE_RETURN 		= "index";
	private static final String PAGE_RETURN_HELLO 	= "hello";
	
	@Autowired
	public CustomerService customerService;
	
    @GetMapping({"/", "/hello"})
    public String hello(Model model,
                        @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        
        System.out.println(customerService.findById(1l));
        
        return PAGE_RETURN;
    }
}
