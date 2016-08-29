package com.abelhzo.spring.controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abelhzo.spring.beans.Roles;
import com.abelhzo.spring.beans.Usuarios;
import com.abelhzo.spring.dao.UsuariosDAO;

@Service("myUserDetailService")
public class UsuariosDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UsuariosDAO dao;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuarios user = dao.getUserByName(username);
		
		if(user != null) {
			
			System.out.println(user.getRoles());
	
			boolean enabled = Boolean.TRUE;
			boolean accountNonExpired = Boolean.TRUE;
			boolean credentialsNonExpired = Boolean.TRUE;
			boolean accountNonLocked = Boolean.TRUE;
	
			Collection<GrantedAuthority> authorities = new ArrayList<>();
	
			System.out.println(user.getUsuario());
			for (Roles rol : user.getRoles()) {
				authorities.add(new SimpleGrantedAuthority(rol.getRol()));
			}
	
			User securityUser = new User(username, 
										user.getPassword(), 
										enabled, 
										accountNonExpired, 
										credentialsNonExpired,
										accountNonLocked, 
										authorities);
			
			return securityUser;
			
		} else {
			
			throw new UsernameNotFoundException("Usuario no Encontrado.");
			
		}

	}

}
