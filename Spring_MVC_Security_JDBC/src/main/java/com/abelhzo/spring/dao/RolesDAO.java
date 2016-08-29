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

@Repository
public class RolesDAO implements RolesService {
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate parameterJdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		parameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public int saveRol(Roles rol) {
		String sql = "INSERT INTO Roles(idRol, rol) VALUES(UNIX_TIMESTAMP(), :name)";
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("name", rol.getRol());
		return parameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public int updateRol(Roles rol) {
		String sql = "UPDATE Roles SET rol = :rol WHERE idRol = :id";
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("id", rol.getIdRol());
		return parameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public Roles getRol(String id) {
		String sql = "SELECT * FROM Roles WHERE idRol = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RolesMapper());
	}

	@Override
	public List<Roles> listRoles() {
		String sql = "SELECT * FROM Roles";
		return jdbcTemplate.query(sql, new RolesMapper());
	}

}
