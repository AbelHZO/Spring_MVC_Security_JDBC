package com.abelhzo.spring.dao;

import java.util.List;

import com.abelhzo.spring.beans.Roles;

public interface RolesService {

	public int saveRol(Roles rol);

	public int updateRol(Roles rol);

	public Roles getRol(String id);

	public List<Roles> listRoles();

}
