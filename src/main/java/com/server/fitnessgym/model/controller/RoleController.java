package com.server.fitnessgym.model.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.fitnessgym.model.dto.RoleDto;
import com.server.fitnessgym.model.entity.Role;
import com.server.fitnessgym.model.service.RoleService;

@Controller
@RequestMapping("/roles")
public class RoleController {

	private RoleService service;
	
	public RoleController(RoleService service) {
		this.service = service;
	}
	
	@GetMapping
	public String list(Map<String, Object> model) {
		return "pages/roles";
	}
	
	public String create(RoleDto request) {
		service.create(toEntity(request));
		return "rolesCreate";
	}
	
	private Role toEntity(RoleDto dto) {
		Role entity = new Role ();
		return entity;
	}
	
} 
