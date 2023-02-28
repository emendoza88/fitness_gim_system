package com.server.fitnessgym.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.fitnessgym.model.dto.InvoiceDto;
import com.server.fitnessgym.model.dto.PaymentsDayRequest;
import com.server.fitnessgym.model.entity.Invoice;
import com.server.fitnessgym.model.service.CustomerService;
import com.server.fitnessgym.model.service.InvoiceService;

@Controller
@RequestMapping(path = "/invoices")
public class InvoiceController {
	
	private InvoiceService service;
	
	@Autowired
	private CustomerService clientService;
	
	public InvoiceController (InvoiceService service) {
		this.service = service;
	}
	
	@GetMapping
	public String create(Model model) {
		model.addAttribute("payment", new PaymentsDayRequest());
		model.addAttribute("userList", clientService.findAll());
		return "pages/invoiceCreate";
	}
	
	@PostMapping
	public String create(InvoiceDto request) {
		Invoice entity = toEntity(request);
		service.create(entity);
		return "pages/invoiceCreate";
	}
	
	private Invoice toEntity(InvoiceDto dto) {
		Invoice entity = new Invoice();
		return entity;
	}
}