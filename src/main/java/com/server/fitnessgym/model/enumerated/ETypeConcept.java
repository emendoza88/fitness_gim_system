package com.server.fitnessgym.model.enumerated;

public enum ETypeConcept {
	
	GYM("Gimnasio"),
	STORE("Tienda");
	
	private String name;
	
	private ETypeConcept(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

}