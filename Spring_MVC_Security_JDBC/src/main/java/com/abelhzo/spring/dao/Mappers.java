package com.abelhzo.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.abelhzo.spring.beans.Roles;
import com.abelhzo.spring.beans.Usuarios;

class UsuariosMapper implements RowMapper<Usuarios> {

	@Override
	public Usuarios mapRow(ResultSet rs, int arg1) throws SQLException {
		Usuarios user = new Usuarios();
		user.setIdUser(rs.getString("idUser"));
		user.setUsuario(rs.getString("usuario"));
		user.setPassword(rs.getString("password"));
		user.setEmail(rs.getString("email"));
		return user;
	}

}

class RolesMapper implements RowMapper<Roles> {

	@Override
	public Roles mapRow(ResultSet rs, int arg1) throws SQLException {
		Roles rol = new Roles();
		rol.setIdRol(rs.getString("idRol"));
		rol.setRol(rs.getString("rol"));
		return rol;
	}
	
}
