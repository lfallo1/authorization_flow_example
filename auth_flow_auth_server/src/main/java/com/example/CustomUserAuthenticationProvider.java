package com.example;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * custom handler during auth process
 * @author lancefallon
 *
 */
@Component("customUserAuthenticationProvider")
public class CustomUserAuthenticationProvider implements AuthenticationProvider {

	private static final Logger LOGGER = Logger.getLogger(CustomUserAuthenticationProvider.class);

	/**
	 * try to authenticate
	 */
	@Override
	public Authentication authenticate(Authentication authentication) {

		LOGGER.info("Inside #CustomUserAuthenticationProvider");

		CustomUserPasswordAuthenticationToken auth = null;
		if (authentication != null) {

			final Object username = authentication.getPrincipal();
			final Object password = authentication.getCredentials();
			
			// dummy authentication
			if(username.toString().toLowerCase().startsWith("lfal")){
				final List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
				String role = username.equals("lfallo1") ? "ROLE_ADMIN" : "ROLE_USER";
				grantedAuthorities.add(new SimpleGrantedAuthority(role));
				auth = new CustomUserPasswordAuthenticationToken(username, password, grantedAuthorities);
				auth.setDetails(username);
			}
		}
		
		//return the auth. if null, same as no auth
		return auth;
	}

	@Override
	public boolean supports(final Class<? extends Object> authentication) {
		return (UsernamePasswordAuthenticationToken.class).isAssignableFrom(authentication);
	}

}
