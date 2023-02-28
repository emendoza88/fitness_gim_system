package com.server.fitnessgym.model.enumerated;

public enum EPaymentMethod {
	
	EFECTIVO("Efectivo"),
	NEQUI("Nequi"),
	DAVIPLATA("Daviplata");
	
	private String name;
	
	private EPaymentMethod(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

}