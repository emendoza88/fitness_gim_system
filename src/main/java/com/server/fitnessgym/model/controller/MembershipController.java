package com.server.fitnessgym.model.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.fitnessgym.model.dto.MembershipDto;
import com.server.fitnessgym.model.entity.Membership;
import com.server.fitnessgym.model.service.MembershipService;
import com.server.fitnessgym.model.service.PeriodicityService;

@Controller
@RequestMapping(path = "/memberships")
public class MembershipController extends GenericController {
	
	private static final String TITLE = "Membresia";
	
	private MembershipService service;
	
	@Autowired
	private PeriodicityService periodicityService;
	
	public MembershipController(MembershipService service) {
		this.service = service;
	}
	
	@Override
	public String title() {
		return TITLE;
	}

	@Override
	public String pathCreate() {
		return "/memberships/create";
	}

	@Override
	public String list(Map<String, Object> model) {
		model.put("membershipsList", service.findAll());
		return "pages/memberships";
	}
	
	@Override
	public String create(Map<String, Object> model) {
		model.put("periodicityList", periodicityService.findAll());
		model.put("memberships", new MembershipDto());
		return "/pages/membershipsCreate";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable Long id) throws Exception {
		Optional<Membership> entity = service.findByID(id);
		Membership membership = entity.orElse(null);
		if(membership != null) {
			model.addAttribute("periodicityList", periodicityService.findAll());
			model.addAttribute("memberships", toDto(membership));
			model.addAttribute("title", TITLE);
			return "/pages/membershipsCreate";
		}
		
		throw new Exception("No existe");
	}
	
	@PostMapping("/create")
	public String create(MembershipDto request) {
		service.create(toEntity(request));
		return "pages/memberships";
	}
	
	private Membership toEntity(MembershipDto dto) {
		Membership entity = new Membership ();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPeriod(dto.getPeriod());
		entity.setPrice(dto.getPrice());
		entity.setState(dto.getState());
		return entity;
	}
	
	private MembershipDto toDto(Membership entity) {
		MembershipDto dto = new MembershipDto ();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDescription(entity.getDescription());
		dto.setPeriod(entity.getPeriod());
		dto.setPrice(entity.getPrice());
		dto.setState(entity.getState());
		return dto;
	}

}