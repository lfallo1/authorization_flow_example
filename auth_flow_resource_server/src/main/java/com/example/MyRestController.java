package com.example;

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
	public ResponseEntity<OAuth2Authentication> doStuff(OAuth2Authentication auth) {
		return new ResponseEntity<>(auth, HttpStatus.OK);
	}

}
