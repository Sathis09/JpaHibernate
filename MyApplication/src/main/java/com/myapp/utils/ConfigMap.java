package com.myapp.utils;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ConfigMap {
	private static ConfigMap INSTANCE = new ConfigMap();
	
	public static ConfigMap getInstance() {
		return INSTANCE;
	}
	
	private ConcurrentHashMap<String, HashMap<String, String>>	configCategoryMap;
	
	public ConcurrentHashMap<String, HashMap<String, String>> getConfigCategoryMap() {
		return configCategoryMap;
	}

	public void setConfigCategoryMap(ConcurrentHashMap<String, HashMap<String, String>> configCategoryMap) {
		this.configCategoryMap = configCategoryMap;
	}
}
