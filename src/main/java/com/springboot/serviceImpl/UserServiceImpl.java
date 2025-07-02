package com.springboot.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.model.Usuario;
import com.springboot.repository.UsuarioRepository;

@Service
public class UserServiceImpl implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = repository.findOneByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("El usuario no se ha encontrado con el email: "+ email));
		
		return new UserDetailImpl(usuario);
	}
	

}
