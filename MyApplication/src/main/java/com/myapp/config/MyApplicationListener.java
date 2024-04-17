package com.myapp.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.myapp.service.UtilityService;
import com.myapp.utils.ConfigMap;

@Component
@Order(0)
public class MyApplicationListener implements ApplicationListener<ApplicationReadyEvent>  {

	@Autowired
	UtilityService utilityService;
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		
		ConfigMap.getInstance().setConfigCategoryMap(utilityService.getConfigParameters());
		
	}

}
