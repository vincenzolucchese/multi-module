package com.vince.accessingdatajpa;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.CollectionUtils;

import com.vince.accessingdatajpa.entity.CategoryModel;
import com.vince.accessingdatajpa.entity.DemoAppModel;
import com.vince.accessingdatajpa.repository.CategoryRepository;
import com.vince.accessingdatajpa.repository.DemoAppRepository;

@SpringBootApplication
public class AccessingDataJpaApplication {

	private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataJpaApplication.class);
	}
	
//	@Bean
//	@Primary
//	@ConfigurationProperties("app.datasource")
//	public DataSourceProperties dataSourceProperties() {
//	    return new DataSourceProperties();
//	}
//
//	@Bean
//	public HikariDataSource dataSource(DataSourceProperties properties) {
//	    return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
//	}

	
	@Bean
	public CommandLineRunner demo() {
		return (args) -> {			
			populate();
		};
	}
	
	@Autowired
	private  CategoryRepository categoryRepository;
	@Autowired
	private  DemoAppRepository demoAppRepository;
	

	private void populate() {
		populateCategory();
		populateDemoApp();
		
		
	}
	
	private void populateCategory() {
		List<CategoryModel> categoryModelList = (List<CategoryModel>) categoryRepository.findAll();
		log.info("-------------------------------");		
		if(CollectionUtils.isEmpty(categoryModelList)) {
			log.info("CategoryModel is empty.");
			categoryRepository.save(new CategoryModel("WEB", "Web app"));
			categoryRepository.save(new CategoryModel("MAIN", "Main local"));
			categoryRepository.save(new CategoryModel("OTHER", "Other"));
		}		
		for (CategoryModel each : categoryRepository.findAll()) {
			log.info(each.toString());
		}
		log.info("");
	}
	
	private void populateDemoApp() {
		List<DemoAppModel> modelList = (List<DemoAppModel>) demoAppRepository.findAll();
		log.info("-------------------------------");		
		if(CollectionUtils.isEmpty(modelList)) {
			log.info("DemoAppModel is empty.");
			DemoAppModel demoAppModel = new DemoAppModel();
			demoAppModel.setCode("PORTAL");
			demoAppModel.setBriefDescription("Web app for managing all demo apps");
			demoAppModel.setLink("http://localhost:8090/portal");
			demoAppModel.setName("Portal");
			demoAppModel.setDescription("Web app for managing all demo apps");
			demoAppModel.setCodeCategory(categoryRepository.findById(1l));
			
			demoAppRepository.save(demoAppModel);
		}		
		for (DemoAppModel each : demoAppRepository.findAll()) {
			log.info(each.toString());
		}
		log.info("");
	}

}
