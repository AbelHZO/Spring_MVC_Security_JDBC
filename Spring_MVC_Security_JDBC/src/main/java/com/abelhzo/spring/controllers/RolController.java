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

import com.abelhzo.spring.beans.Roles;
import com.abelhzo.spring.dao.RolesDAO;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class RolController {

	@Autowired
	private RolesDAO dao;

	@RequestMapping(value = "/abelhzo/saverol", method = RequestMethod.POST, produces = "application/json")
	private @ResponseBody String saveRol(@RequestBody Roles rol) {
		return "{\"status\":\"" + dao.saveRol(rol) + "\"}";
	}

	@RequestMapping(value = "/abelhzo/updaterol", method = RequestMethod.POST, produces = "application/json")
	private @ResponseBody String updateRol(@RequestBody Roles rol) {
		return "{\"status\":\"" + dao.updateRol(rol) + "\"}";
	}

	@RequestMapping(value = "/abelhzo/getrol", method = RequestMethod.POST, produces = "application/json")
	private @ResponseBody String getRol(@PathVariable String id) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(dao.getRol(id));
	}

	@RequestMapping(value = "/abelhzo/listroles", method = RequestMethod.GET, produces = "application/json")
	private @ResponseBody String listRoles() throws JsonGenerationException, JsonMappingException, IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(out, dao.listRoles());
		return new String(out.toByteArray());
	}

}
