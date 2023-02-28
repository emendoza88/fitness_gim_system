package com.server.fitnessgym.model.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.fitnessgym.model.dto.RegistryCustomerDto;
import com.server.fitnessgym.model.entity.Customer;
import com.server.fitnessgym.model.entity.Registry;
import com.server.fitnessgym.model.service.CustomerService;
import com.server.fitnessgym.model.service.RegistryService;

@Controller
@RequestMapping(path = "/registry-visit")
public class RegistryController extends GenericController {

	private RegistryService service;
	
	private CustomerService clientService;
	
	public RegistryController(RegistryService service, CustomerService clientService) {
		this.service = service;
		this.clientService = clientService;
	}
	
	@Override
	public String title() {
		return "Registrar visita";
	}

	@Override
	public String pathCreate() {
		return null;
	}

	@Override
	public String create(Map<String, Object> model) {
		model.put("registry", new RegistryCustomerDto());
		model.put("userList", clientService.findAll());
		return "pages/registryCreate";
	}
	
	@Override
	public String list(Map<String, Object> model) {
		model.put("registryList", service.findAll());
		return "pages/registries";
	}
	
	@PostMapping("/create")
	public String create(RegistryCustomerDto request) {
		service.create(toEntity(request));
		return "redirect:/registry-visit/create";
	}

	private Registry toEntity(RegistryCustomerDto request) {
		Registry entity = new Registry();
		entity.setUser(new Customer(request.getUser()));
		return entity;
	}

}