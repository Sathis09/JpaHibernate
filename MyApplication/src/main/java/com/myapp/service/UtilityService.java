package com.myapp.service;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
@Service
public interface UtilityService {
	
	public ConcurrentHashMap<String, HashMap<String, String>> getConfigParameters() ;

	public void setStatusMaster();

	public void setAddressType();
		
}
