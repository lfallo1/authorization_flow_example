package com.example;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/resource")
public class MyRestController {

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Map<String, String>> doStuff(OAuth2Authentication auth) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", auth.getName());
		map.put("credentials", auth.getCredentials().toString());
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

}
