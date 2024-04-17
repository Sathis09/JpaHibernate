package com.myapp.utils;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class RedisUtil {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public Boolean storeMap(String key, Map<String, String> map) {
		try {
			HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
			hashOps.putAll(key, map);
			log.info("Successfully Stored the Key={}", key);
			return true;
		} catch (Exception ex) {
			log.info("Error while Storing the key={},ex={}", key, ex);
			return false;
		}
	}
	public String getValue(String Map,String key) {
		try {
			HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
			String value = hashOps.get(Map,key);
			log.info("Successfully Stored the Key={}", key);
			return value;
		} catch (Exception ex) {
			log.info("Error while Storing the key={},ex={}", key, ex);
			return null;
		}
	}

}
