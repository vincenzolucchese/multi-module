package com.vince.portal.controller;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vince.multimodule.data.CategoryData;
import com.vince.multimodule.data.DemoAppData;
import com.vince.portal.abs.controller.AbstractDetailController;


@Controller
public class DemoAppDetailController extends AbstractDetailController {
	
	private static final String PAGE_RETURN 		= "demoAppDetail";
	
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
        model.addAttribute("categoryList", getAllCategory()); 
        
        return PAGE_RETURN;
    }
    
    @PostMapping({"/portfolio/new"})
    public String createPost(Model model, DemoAppData demoApp ) {
       
    	demoApp = demoAppFacade.save(demoApp);
    	model.addAttribute("demoApp", demoApp);  
    	model.addAttribute("categoryList", getAllCategory());
    	
        return PAGE_RETURN;
    }
    
    
    private Map<String, String> getAllCategory() {
		Collection<CategoryData> listData = categoryFacade.findAll();
		
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("", "");
		for (CategoryData each : listData) {
			map.put(""+each.getId(), each.getDescription());			
		}
		
		return map;
    }
}
