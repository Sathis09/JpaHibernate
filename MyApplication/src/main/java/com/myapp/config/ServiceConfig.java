package com.myapp.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@RefreshScope
public class ServiceConfig {

//    @Value("${cdsai.security.oauth2.clientId}")
//    private String clientID;
//
//    @Value("${cdsai.security.oauth2.client.client-secret}")
//    private String clientSecret;
//
//    @Value("${cdsai.security.oauth2.resource.token-info-uri}")
//    private String uri;
//	
//	@Value("${api.service.timeout}")
//    private Integer apiServiceTimeout;
//	
//	@Value("${env.api.url}")
//	private String envApiUrl; 
    
}
