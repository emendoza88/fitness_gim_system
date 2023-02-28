package com.server.fitnessgym.model.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;

public abstract class GenericController {
	
	public abstract String title();
	
	public abstract String pathCreate();
	
	public abstract String list(Map<String, Object> model);
	
	public abstract String create(Map<String, Object> model);
	
	@GetMapping
	public String listGeneric(Map<String, Object> model) {
		model.put("title", title());
		model.put("path_create", pathCreate());
		return list(model);
	}
	
	@GetMapping("/create")
	public String createGeneric(Map<String, Object> model) {
		model.put("title", title());
		return create(model);
	}
	

}