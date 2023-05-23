package com.residencia.atividadeAPI.security.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.residencia.atividadeAPI.security.services.UserDetailsImpl;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${app.jwt.secret}")
	private String jwtSecret;

	@Value("${app.jwt.expiration.ms}")
	private int jwtExpirationMs;

	public String generateJwtToken(Authentication authentication) {

		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		/*
		Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(jwtSecret), 
                SignatureAlgorithm.HS256.getJcaName());
        */
		SecretKey sKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
		
		return Jwts.builder()
					.setSubject((userPrincipal.getUsername()))
					.setIssuedAt(new Date())
					.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
					//.signWith(hmacKey)
					.signWith(sKey, SignatureAlgorithm.HS256)
					//.encryptWith(jwtSecret, key , "HS256")
					.compact();
	}

	public String getUserNameFromJwtToken(String token) {
		//Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(jwtSecret), 
                SignatureAlgorithm.HS256.getJcaName());
		SecretKey sKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
		return Jwts.parserBuilder()
				//.setSigningKey(hmacKey)
				.setSigningKey(sKey)
				.build()
				.parseClaimsJws(token)
				.getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken).getBody().getSubject();
			return true;
		}catch (JwtException e) {
			logger.error("Token JWT inválido: {}", e.getMessage());
		}
		return false;
	}
}