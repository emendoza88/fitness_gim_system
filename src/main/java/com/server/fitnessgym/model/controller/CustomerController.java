package com.server.fitnessgym.model.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.fitnessgym.model.dto.CustomerDto;
import com.server.fitnessgym.model.dto.ISubscriptionResultSet;
import com.server.fitnessgym.model.dto.PaymentsSubscriptionRequest;
import com.server.fitnessgym.model.dto.SubscriptionDto;
import com.server.fitnessgym.model.entity.Customer;
import com.server.fitnessgym.model.entity.Role;
import com.server.fitnessgym.model.service.CustomerService;
import com.server.fitnessgym.model.service.MembershipService;
import com.server.fitnessgym.model.service.ProcessPaymentService;

@Controller
@RequestMapping(path = "/customers")
public class CustomerController extends GenericController {
	
	private static final String TITLE = "Clientes";
	
	private static final String PATH_CREATE = "/customers/create";
	
	private CustomerService service;
	
	private MembershipService membershipService;
	
	private ProcessPaymentService paymentService;
	
	public CustomerController(CustomerService service, MembershipService membershipService, ProcessPaymentService paymentService) {
		this.service = service;
		this.membershipService = membershipService;
		this.paymentService = paymentService;
	}
	
	@Override
	public String title() {
		return TITLE;
	}

	@Override
	public String pathCreate() {
		return PATH_CREATE;
	}
	
	@Override
	public String create(Map<String, Object> model) {
		model.put("customer", new CustomerDto());
		model.put("membershipsList", membershipService.findAll());
		return "pages/customerCreate2";
	}
	
	@Override
	public String list(Map<String, Object> model) {
		LocalDate currentDate = LocalDate.now();
		List<ISubscriptionResultSet> findSuscriptionClient = service.findSuscriptionClient();
		List<SubscriptionDto> dtos = findSuscriptionClient.stream().map(e -> {
			SubscriptionDto s = new SubscriptionDto();
			s.setId(e.getUserId());
			s.setEmail(e.getEmail());
			s.setExpirationDate(e.getExpirationDate());
			s.setName(e.getName());
			s.setNameMembership(e.getNameMembership());
			s.setPhone(e.getPhone());
			s.setStartDate(e.getStartDate());
			s.setSurname(e.getSurname());
			s.setUsername(e.getUsername());
			LocalDate expirationLocalDate = e.getExpirationDate() != null ? e.getExpirationDate().toLocalDate() : null;
			s.setState(expirationLocalDate != null && (expirationLocalDate.isEqual(currentDate) || expirationLocalDate.isAfter(currentDate)) ? 1: 0);
			
			return s;
		}).collect(Collectors.toList());
		
		model.put("clientList", dtos);
		return "pages/customers";
	}
	
	@PostMapping("/create")
	public String create(CustomerDto request) {
		service.create(toEntity(request));
		return "redirect:/customers";
	}
	
	@PostMapping("/create-payment")
	public String payment(CustomerDto request) {
		Customer customer = service.createWhitSupscription(toEntity(request));
		PaymentsSubscriptionRequest paymentsRequest = new PaymentsSubscriptionRequest(customer.getId(), customer.getSupscription(), request.getPaymentMethod());
		paymentService.paymentSubscriptionProcess(paymentsRequest);
		return "redirect:/customers";
	}
	
	private Customer toEntity(CustomerDto dto) {
		Customer entity = new Customer ();
		entity.setName(dto.getName());
		entity.setSurname(dto.getSurname());
		entity.setEmail(dto.getEmail());
		entity.setPhone(dto.getPhone());
		entity.setUsername(dto.getUsername());
		entity.setPassword(dto.getPassword());
		entity.setRole(new Role(2L));
		entity.setMemberships(dto.getMemberships());
		return entity;
	}
	
	@SuppressWarnings("unused")
	private CustomerDto toDto(Customer entity) {
		CustomerDto dto = new CustomerDto ();
		dto.setName(entity.getName());
		dto.setSurname(entity.getSurname());
		dto.setEmail(entity.getEmail());
		dto.setPhone(entity.getPhone());
		return dto;
	}
	
}