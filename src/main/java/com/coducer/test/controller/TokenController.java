package com.coducer.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.coducer.test.dto.TokenResponse;
import com.coducer.test.entity.User;
import com.coducer.test.jwtauth.JwtUtils;
import com.coducer.test.service.TokenService;

@RestController
@RequestMapping("/service/v1/token")
public class TokenController {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping
	public ResponseEntity<Object> getCurrentToken(@RequestBody User user) {

		try {
			User userName = tokenService.findByUsername(user.getUsername());

			if (userName == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Username");
			}

			User user1 = tokenService.findByUsernameAndPassword(user.getUsername(), user.getPassword());

			if (user1 == null) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body("Authorization failure !!..Please Check Username Or Password");
			}
			String token = jwtUtils.generateJwtToken(user1);

			TokenResponse tk = new TokenResponse();
			tk.setToken(token);

			return ResponseEntity.ok(tk);

		} catch (Exception e) {

			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
