package com.server.fitnessgym.model.controller;

import com.server.fitnessgym.model.dto.ISubscriptionResultSet;
import com.server.fitnessgym.model.entity.Subscription;
import com.server.fitnessgym.model.service.SubscriptionService;

public class SubscriptionController {
	
	private SubscriptionService service;
	
	public SubscriptionController(SubscriptionService service) {
		this.service = service;
	}
	
	public String create(ISubscriptionResultSet request) {
		service.create(toEntity(request));
		return "createSuscription";
	}
	
	private Subscription toEntity(ISubscriptionResultSet dto) {
		Subscription entity = new Subscription ();
		return entity;
	}

}