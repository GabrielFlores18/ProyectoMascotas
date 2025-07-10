package com.springboot.util;

import java.sql.Date;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class Token {

	private final static String TOKEN_SECRETO = "eH0lGylb6eoiIu5rIkYwBMZ9ZeeW354k";
	private final static Long TOKEN_DURACION = 3_600L;
	
	public static String crearToken(String user, String email) {
		long expiracionTiempo = TOKEN_DURACION * 1_000L;
		Date expiracionFecha = new Date(System.currentTimeMillis() + expiracionTiempo);
		
		Map<String, Object> map = new HashMap<>();
		map.put("nombre", user);
		
		return Jwts.builder()
				.setSubject(email)
				.setExpiration(expiracionFecha)
				.addClaims(map)
				.signWith(Keys.hmacShaKeyFor(TOKEN_SECRETO.getBytes()))
				.compact();
				
	}
	
	public static UsernamePasswordAuthenticationToken getauth(String token) {
		try {
			Claims claims = Jwts.parserBuilder()
					.setSigningKey(TOKEN_SECRETO.getBytes())
					.build()
					.parseClaimsJws(token)
					.getBody();
			
			String email = claims.getSubject();
			return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
			
		}catch (Exception e) {
			System.out.println("Error en el metodo {UsernamePasswordAuthenticationToken(): }" + e.getMessage());
			return null;
		}
	}
}
