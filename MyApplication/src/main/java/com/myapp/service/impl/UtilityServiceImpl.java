package com.myapp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.entity.AddressTypeMasterEntity;
import com.myapp.entity.StatusMasterEntity;
import com.myapp.repository.AddressTypeMasterRespository;
import com.myapp.repository.StatusMasterRepository;
import com.myapp.service.UtilityService;
import com.myapp.utils.ConfigMap;
import com.myapp.utils.ConstantUtil;
import com.myapp.utils.RedisUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UtilityServiceImpl implements UtilityService {

	@Autowired
	AddressTypeMasterRespository addressTypeMasterRespository;

	@Autowired
	StatusMasterRepository statusMasterRepository;
	

	@Autowired
	RedisUtil redisUtil;

	@Override
	public ConcurrentHashMap<String, HashMap<String, String>> getConfigParameters() {
		setStatusMaster();
		setAddressType();
		return ConfigMap.getInstance().getConfigCategoryMap();
		
	}

	@Override
	public void setStatusMaster() {
		log.info("Entry point setStatusMaster");
		HashMap<String, String> statusMap = new HashMap<String, String>();

		List<StatusMasterEntity> statusMasterEntityList = statusMasterRepository.findAll();

		for (StatusMasterEntity statusMasterEntity : statusMasterEntityList) {
			statusMap.put(statusMasterEntity.getStatusShortName(), statusMasterEntity.getStatusFullName());
		}

		log.info("statusMap={}", statusMap);
		ConcurrentHashMap<String, HashMap<String, String>> configCategoryMap = ConfigMap.getInstance()
				.getConfigCategoryMap();
		if (null == configCategoryMap) {
			configCategoryMap = new ConcurrentHashMap<String, HashMap<String, String>>();
		}
			

		//configCategoryMap.put(ConstantUtil.MASTER_STATUS, statusMap);
		//ConfigMap.getInstance().setConfigCategoryMap(configCategoryMap);
		
		Boolean result = redisUtil.storeMap(ConstantUtil.MASTER_STATUS, statusMap);
		log.info("Result of Storing the Grievance Status ={}",result);
		log.info("Exit point of the setStatusMaster");
	}

	@Override
	public void setAddressType() {
		log.info("Entry point setAddressType");
		HashMap<String, String> addressTypeMap = new HashMap<String, String>();

		List<AddressTypeMasterEntity> addressTypeMasterEntityList = addressTypeMasterRespository.findAll();

		for (AddressTypeMasterEntity addressTypeMasterEntity : addressTypeMasterEntityList) {
			addressTypeMap.put(addressTypeMasterEntity.getAddressShortName(), addressTypeMasterEntity.getAddressFullName());
		}

		log.info("addressTypeMap={}", addressTypeMap);
		ConcurrentHashMap<String, HashMap<String, String>> configCategoryMap = ConfigMap.getInstance()
				.getConfigCategoryMap();
		if (null == configCategoryMap) {
			configCategoryMap = new ConcurrentHashMap<String, HashMap<String, String>>();
		}
			

		configCategoryMap.put(ConstantUtil.MASTER_ADDRESS_TYPE, addressTypeMap);
		ConfigMap.getInstance().setConfigCategoryMap(configCategoryMap);
		log.info("Exit point of the setAddressType");
	}

}
