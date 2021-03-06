package com.vince.multimodule.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.vince.accessingdatajpa.entity.CategoryModel;
import com.vince.accessingdatajpa.repository.CategoryRepository;
import com.vince.multimodule.data.CategoryData;
import com.vince.multimodule.facade.CategoryFacade;

@Service
public class CategoryFacadeImpl implements CategoryFacade {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<CategoryData> findAll() {
		List<CategoryData> listData = new ArrayList<>();
		List<CategoryModel> listModel = (List<CategoryModel>) categoryRepository.findAll();
		if(!CollectionUtils.isEmpty(listModel)) {
			for (CategoryModel each : listModel) {
				listData.add(convertModelToData(each));				
			}
		}
		return listData;
	}
	
	public static CategoryData convertModelToData(CategoryModel model) {
		CategoryData category = new CategoryData();
		BeanUtils.copyProperties(model, category);
		return category;
	}



}
