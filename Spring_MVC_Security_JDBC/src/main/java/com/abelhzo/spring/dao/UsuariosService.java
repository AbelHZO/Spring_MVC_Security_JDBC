package com.abelhzo.spring.dao;

import java.util.List;

import com.abelhzo.spring.beans.Roles;
import com.abelhzo.spring.beans.Usuarios;

public interface UsuariosService {

	public int saveUser(Usuarios user);

	public int updateUser(Usuarios user);

	public Usuarios getUser(String id);

	public List<Usuarios> listUser();
	
	public Usuarios getUserByName(String user);
	
	public List<Roles> getRolesByUser(String idUser);

}
