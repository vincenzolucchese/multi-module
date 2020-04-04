package com.vince.multimodule.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.vince.accessingdatajpa.entity.CategoryModel;
import com.vince.accessingdatajpa.entity.DemoAppModel;
import com.vince.accessingdatajpa.repository.CategoryRepository;
import com.vince.accessingdatajpa.service.DemoAppService;
import com.vince.multimodule.data.DemoAppData;
import com.vince.multimodule.facade.DemoAppFacade;

@Service
public class DemoAppFacadeImpl implements DemoAppFacade {
	
	@Autowired
	private DemoAppService demoAppService;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public DemoAppData save(DemoAppData demoAppData) {
		DemoAppModel entity = new DemoAppModel();
		BeanUtils.copyProperties(demoAppData, entity);
		CategoryModel categoryModel = new CategoryModel(demoAppData.getCodeCategory().getId());
		
		entity.setCodeCategory(categoryModel);
		entity = demoAppService.getRepository().save(entity);
		
		DemoAppData dataReturn = new DemoAppData();
		BeanUtils.copyProperties(entity, dataReturn);
		return dataReturn;
	}

	@Override
	public List<DemoAppData> search(DemoAppData demoAppData) {
		DemoAppModel entity = new DemoAppModel();
		BeanUtils.copyProperties(demoAppData, entity);
		List<DemoAppModel> listResult = (List<DemoAppModel>) demoAppService.getRepository().findAll();
		
		List<DemoAppData> listReturn = null;
		if(!CollectionUtils.isEmpty(listResult)) {
			listReturn = new ArrayList<>();
			for (DemoAppModel each : listResult) {
				listReturn.add(convertModelToData(each));
			}
		}		
		return listReturn;
	}
	
	public static DemoAppData convertModelToData(DemoAppModel model) {
		DemoAppData data = new DemoAppData();
		BeanUtils.copyProperties(model, data);
		
		data.setCodeCategory(CategoryFacadeImpl.convertModelToData(model.getCodeCategory()));
		return data;
	}

	@Override
	public DemoAppData findByCode(String code) {
		DemoAppModel model = demoAppService.findByCode(code);

		if(model!=null) {
			return convertModelToData(model);
		}		
		return null;
	}

}
