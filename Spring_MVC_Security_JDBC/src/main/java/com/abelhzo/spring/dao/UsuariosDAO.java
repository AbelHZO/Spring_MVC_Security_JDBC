package com.abelhzo.spring.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.abelhzo.spring.beans.Roles;
import com.abelhzo.spring.beans.Usuarios;

@Repository
public class UsuariosDAO implements UsuariosService {
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate parameterJdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		parameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public int saveUser(Usuarios user) {
		String sql = "INSERT INTO Usuarios(idUser, usuario, password, email) VALUES(UNIX_TIMESTAMP(), :user, :pass, :email);";
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("user", user.getUsuario())
				.addValue("pass", user.getPassword()).addValue("email", user.getEmail());

		return parameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public int updateUser(Usuarios user) {
		String sql = "UPDATE Usuarios SET usuario = :user, password = :pass, email = :email WHERE idUser = :id;";
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("user", user.getUsuario())
				.addValue("pass", user.getPassword()).addValue("email", user.getEmail())
				.addValue("id", user.getIdUser());

		return parameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public Usuarios getUser(String id) {
		String sql = "SELECT r.* FROM User_Roles ur, Roles r WHERE r.idRol = ur.idUser AND idUser = ?;";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new UsuariosMapper());
	}

	@Override
	public List<Usuarios> listUser() {
		String sql = "SELECT * FROM Usuarios;";
		return jdbcTemplate.query(sql, new UsuariosMapper());
	}

	@Override
	public Usuarios getUserByName(String user) {
		String sql = "SELECT * FROM Usuarios WHERE usuario COLLATE latin1_general_cs = ?";
		Usuarios usuario = jdbcTemplate.queryForObject(sql, new UsuariosMapper(), user);
		usuario.setRoles(getRolesByUser(usuario.getIdUser()));
		return usuario;
	}

	@Override
	public List<Roles> getRolesByUser(String idUser) {
		String sql = "SELECT r.* FROM User_Roles ur, Roles r WHERE r.idRol = ur.idRol AND ur.idUser = ?";
		return jdbcTemplate.query(sql, new Object[] { idUser }, new RolesMapper());
	}

}
