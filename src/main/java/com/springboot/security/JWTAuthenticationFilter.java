package com.springboot.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.model.Auth;
import com.springboot.serviceImpl.UserDetailImpl;
import com.springboot.util.Token;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		Auth auth = new Auth();
		
		try {
			auth = new ObjectMapper().readValue(request.getReader(), Auth.class);
		}catch(StreamReadException e) {
			e.printStackTrace();
		}catch(DatabindException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		UsernamePasswordAuthenticationToken userPaT = new UsernamePasswordAuthenticationToken(
				auth.getEmail(),
				auth.getPassword(),
				Collections.emptyList());
		
		return getAuthenticationManager().authenticate(userPaT);
	}

	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		UserDetailImpl userDetails = (UserDetailImpl) authResult.getPrincipal();
		String token = Token.crearToken(userDetails.getUser(), userDetails.getUsername());
		response.addHeader("Authorization", "Bearer" + token);
		
		response.getWriter().flush();
		
		super.successfulAuthentication(request, response, chain, authResult);
	}
}
