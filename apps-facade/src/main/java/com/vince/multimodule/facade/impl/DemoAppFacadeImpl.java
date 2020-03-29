package com.vince.multimodule.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.vince.accessingdatajpa.entity.DemoAppModel;
import com.vince.accessingdatajpa.service.DemoAppService;
import com.vince.multimodule.data.DemoAppData;
import com.vince.multimodule.facade.DemoAppFacade;

@Service
public class DemoAppFacadeImpl implements DemoAppFacade {
	
	@Autowired
	private DemoAppService demoAppService;

	@Override
	public DemoAppData save(DemoAppData demoAppData) {
		DemoAppModel entity = new DemoAppModel();
		BeanUtils.copyProperties(demoAppData, entity);
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
				DemoAppData dataReturn = new DemoAppData();
				BeanUtils.copyProperties(each, dataReturn);
				listReturn.add(dataReturn);
			}
		}		
		return listReturn;
	}

}
