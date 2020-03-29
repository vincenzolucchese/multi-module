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
import com.vince.accessingdatajpa.repository.CategoryRepository;

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
	

	private void populate() {
		List<CategoryModel> categoryModelList = (List<CategoryModel>) categoryRepository.findAll();
		
		if(CollectionUtils.isEmpty(categoryModelList)) {
			categoryRepository.save(new CategoryModel("WEB", "Web app"));
			categoryRepository.save(new CategoryModel("OTHER", "Other"));
		}

		log.info("-------------------------------");
		for (CategoryModel each : categoryRepository.findAll()) {
			log.info(each.toString());
		}
		log.info("");
		
	}

}
