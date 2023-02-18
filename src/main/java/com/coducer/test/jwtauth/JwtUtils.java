package com.coducer.test.jwtauth;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.coducer.test.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.expirationMs}")
	private int jwtExpirationMs;

	public String generateJwtToken(User user) {
		try {
			Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

			return Jwts.builder().setSubject((String.valueOf(user.getId()))).setIssuedAt(new Date())
					.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
					.signWith(key, SignatureAlgorithm.HS512).compact();

		} catch (Exception e) {

			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes()).build().parseClaimsJws(authToken);

			return true;

		} catch (Exception e) {
			return false;

		}
	}

	public String getUserIdFromJwtToken(String token) {
		try {
			Claims claims = Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes()).build().parseClaimsJws(token)
					.getBody();

			return claims.getSubject();

		} catch (Exception e) {

			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	public String getBearerToken(String authorizationHeader) {
		try {

			if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {

				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid authorization header");
			}

			String token = authorizationHeader.substring(7);

			return token;

		} catch (Exception e) {

			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
