package com.vince.accessingdatajpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.vince.accessingdatajpa.entity.CategoryModel;
import com.vince.accessingdatajpa.entity.CustomerModel;

public interface CategoryRepository extends CrudRepository<CategoryModel, Long> {
	
	CategoryModel findById(long id);
}
