package com.vince.portal.controller;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
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
	
	private static final String PAGE_RETURN 			= "demoApp/demoAppDetail";
	private static final String PAGE_RETURN_STATUS 		= "demoApp/demoAppStatus";
	
	private HashMap<String, Process> mapProcess = new HashMap<>();

    @GetMapping({"/portfolio/start"})
    public String start(Model model,
                        @RequestParam(value="code", required=true, defaultValue="World") String code) {
        model.addAttribute("demoApp", demoAppFacade.findByCode(code));  
        execute("start", code);
       
        return PAGE_RETURN_STATUS;
    }
    
    @GetMapping({"/portfolio/stop"})
    public String stop(Model model,
                        @RequestParam(value="code", required=true, defaultValue="World") String code) {
        model.addAttribute("demoApp", demoAppFacade.findByCode(code));  
        mapProcess.get(code).destroy();
        mapProcess.remove(code);
        
        return PAGE_RETURN_STATUS;
    }
	
	@GetMapping({"/portfolio/detail"})
    public String hello(Model model,
                        @RequestParam(value="code", required=true, defaultValue="World") String code) {
        model.addAttribute("demoApp", demoAppFacade.findByCode(code));        
       
        return PAGE_RETURN_STATUS;
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
    
    //######################################################
    //######################################################
    //######################################################
    
    public void execute(String command, String code) {

//        ExecutorService pool = Executors.newSingleThreadExecutor();

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.directory(new File("../form-submission/"));
    	// -- Linux --
    	// Run a shell command
        processBuilder.command("bash", "-c", "java -jar target/form-submission-0.0.1-SNAPSHOT.jar");
    	// Run a shell script
    	//processBuilder.command("path/to/hello.sh");

    	// -- Windows --
    	// Run a command
    	//processBuilder.command("cmd.exe", "/c", "dir C:\\Users\\mkyong");
    	// Run a bat file
    	//processBuilder.command("C:\\Users\\mkyong\\hello.bat");

        try {

           Process process = processBuilder.start();      
           mapProcess.put(code, process);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//           pool.shutdown();
        }
    }

}
