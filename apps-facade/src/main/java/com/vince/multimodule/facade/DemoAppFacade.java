package com.vince.multimodule.facade;

import java.util.List;

import com.vince.multimodule.data.DemoAppData;

public interface DemoAppFacade {
	
	public DemoAppData save(DemoAppData demoAppData);
	public List<DemoAppData> search(DemoAppData demoAppData);

}
