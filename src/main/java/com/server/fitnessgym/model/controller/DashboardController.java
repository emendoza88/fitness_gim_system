package com.server.fitnessgym.model.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.fitnessgym.model.service.DashboardService;

@Controller
@RequestMapping(path = {"/dashboard", ""})
public class DashboardController {
	
	private static final String TITLE = "dashboard";
	
	
	private DashboardService service;
	
	
	public DashboardController(DashboardService service) {
		this.service = service;
	}
	
	@GetMapping
	public String paymnetDay(Model model) {
		model.addAttribute("totalDay", service.getTotalEarningsDay());
		model.addAttribute("totalMonth", service.getTotalEarningsMonth());
		model.addAttribute("totalUser", service.getTotalUserActive());
		model.addAttribute("title", TITLE);
		return "index";
	}
	
}