package com.abelhzo.spring.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abelhzo.spring.beans.Usuarios;
import com.abelhzo.spring.dao.UsuariosDAO;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class UsuarioController {

	@Autowired
	private UsuariosDAO dao;

	@RequestMapping(value = "/abelhzo/users")
	private String goUserPage() {
		return "usuarios";
	}

	@RequestMapping(value = "/abelhzo/saveuser", method = RequestMethod.POST, produces = "application/json")
	private @ResponseBody String saveUser(@RequestBody String json)
			throws JsonParseException, JsonMappingException, IOException {
		Usuarios usuario = (Usuarios) new ObjectMapper().readValue(json, Usuarios.class);
		return "{\"status:\":\"" + dao.saveUser(usuario) + "\"}";
	}

	@RequestMapping(value = "/abelhzo/updateuser", method = RequestMethod.PUT, produces = "application/json")
	private @ResponseBody String updateUser(@RequestBody Usuarios usuario) {
		return "{\"status\":\"" + dao.updateUser(usuario) + "\"}";
	}

	@RequestMapping(value = "/abelhzo/getuser/{id}", method = RequestMethod.POST, produces = "application/json")
	private @ResponseBody String getUser(@PathVariable("id") String id) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(dao.getUser(id));
	}

	@RequestMapping(value = "/abelhzo/listuser", method = RequestMethod.GET, produces = "application/json")
	private @ResponseBody String listUser() throws JsonGenerationException, JsonMappingException, IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(baos, dao.listUser());
		return new String(baos.toByteArray());
	}

}
