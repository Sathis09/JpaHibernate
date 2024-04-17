package com.myapp.security;
/*package com.gov.bihar.cbs.config.security;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gov.bihar.cbs.model.StatusResponse;
import com.gov.bihar.cbs.model.exception.ErrorMessage;
import com.gov.bihar.cbs.service.FeatureService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Order(Ordered.LOWEST_PRECEDENCE)
public class AuthorizationFilter implements Filter {
	
	@Autowired
	WebClient client;
	
	@Autowired
	FeatureService featureService;

    public AuthorizationFilter() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        
        Map<String, String> map = new HashMap<>();

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            if(key.equals("content-type")) {
            	String value = request.getHeader(key);
                response.addHeader(key, value);
                map.put(key, value);
            }            
        }
        
        log.info("AuthorizationFilter intercepting request. Trying to accure resource for Path,Method: {} {}", request.getRequestURI(),request.getMethod());
        String method=null;
        String path=null;
        
        int bodyLength = request.getContentLength();
        path = request.getRequestURI();  
        method=getMethod(request.getMethod(),bodyLength);  
        //chain.doFilter(req, res); //to test the alert stream
        
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        log.info("Checking user permission for [userId: {}] [method: {}] [endpoint: {}]",userId,method,path);
        
        StatusResponse statusReponse=StatusResponse.builder().statusCode("403")
    			.error(ErrorMessage.builder().errorId(UUID.randomUUID().toString())
    					.errorCode("CSMS_MDS_ERR_403")
    					.errorMessage("User is NOT-AUTHORIZED to access the requested resource").build()).build();
    	String json = new ObjectMapper().writeValueAsString(statusReponse);
        
        if (StringUtils.isBlank(path) || StringUtils.isBlank(method)) {
        	log.error("Method Or Path is null/empty. User is NOT-AUTHORIZED for [userId: {}] [method: {}] [path: {}]",userId,method,path);
        	response.getWriter().write(json);
        	
        	//response.sendError(HttpServletResponse.SC_FORBIDDEN, "User is not allowed to access the requested resource.");
        	response.flushBuffer();
        } else {
        	if (!featureService.isAuthorized(userId,method,path).block()) {
        		log.error("User is NOT-AUTHORIZED for [userId: {}] [method: {}] [path: {}]",userId,method,path);
            	response.getWriter().write(json);
            	
            	//response.sendError(HttpServletResponse.SC_FORBIDDEN, "User is not allowed to access the requested resource.");
            	response.flushBuffer();
        	} else {
        		log.info("User is AUTHORIZED for [userId: {}] [method: {}] [path: {}]",userId,method,path);
            	chain.doFilter(req, res);
            }
        }
    }

	@Override
    public void init(FilterConfig filterConfig) {
    	log.info("Initializing AuthorizationFilter...");
    }

    @Override
    public void destroy() {
    }
	
	private String getMethod(String method,int bodyLength) {
		String action = null;
		if(StringUtils.isBlank(method)) {
			return action;
		} else if(method.equalsIgnoreCase("OPTIONS")) {
			if(bodyLength>0) {
				action = "WRITE";
			} else {
				action = "READ";
			}			
		} else {
			action=method.equals("GET")?"READ":"WRITE";
		}
		return action;
	}
}
*/