package com.myapp.model.key;

import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import lombok.Data;

@Data
public class SecurityKey {

	private Map<String,SecretKeySpec> securityKeyMap;

}
