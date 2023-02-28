package com.server.fitnessgym.model.controller;

import com.server.fitnessgym.model.service.OptionService;

public class OptionController {
	
	private OptionService service;
	
	public OptionController (OptionService service){
		this.service = service;
	}
}
