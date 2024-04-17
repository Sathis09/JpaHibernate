package com.myapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import com.myapp.config.ServiceConfig;

@Configuration
@EnableResourceServer
@RefreshScope
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Autowired
	ServiceConfig serviceConfig;

//	@Bean
//	@Primary
//	public ResourceServerTokenServices tokenServices() {
//		RemoteTokenServices tokenServices = new RemoteTokenServices();
//		tokenServices.setClientId(serviceConfig.getClientID());
//		tokenServices.setClientSecret(serviceConfig.getClientSecret());
//		tokenServices.setCheckTokenEndpointUrl(serviceConfig.getUri());
//		return tokenServices;
//	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.antMatcher("/gs/**")
			.authorizeRequests()
			.antMatchers("/gs/**").permitAll()
            .antMatchers("/swagger-ui/index.html/**").permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.exceptionHandling()
			.accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		//resources.tokenServices(tokenServices()).resourceId("resource_id");
	}
}
