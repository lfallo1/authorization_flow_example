package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@SpringBootApplication
public class ResourceserverApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(ResourceserverApplication.class, args);
	}
	
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    OAuth2AuthenticationManager authenticationManager = new OAuth2AuthenticationManager();
	    authenticationManager.setTokenServices(tokenService());
	    return authenticationManager;
	}
	
	@Bean
    public AccessTokenConverter accessTokenConverter() {
        return new DefaultAccessTokenConverter();
    }
	
	@Primary
	@Bean
	public RemoteTokenServices tokenService() {
		RemoteTokenServices tokenService = new RemoteTokenServices();
	    tokenService.setCheckTokenEndpointUrl(
	      "http://localhost:9000/oauth/check_token");
	    tokenService.setClientId("acme");
	    tokenService.setClientSecret("acmesecret");
	    tokenService.setAccessTokenConverter(accessTokenConverter());
	    return tokenService;
	}

	@Configuration
	@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
	@EnableResourceServer
	protected static class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

		@Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId("RESOURCE_ID");
        }
		
		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.csrf().disable().authorizeRequests().anyRequest().authenticated().and()
					.exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
		}

	}
}
