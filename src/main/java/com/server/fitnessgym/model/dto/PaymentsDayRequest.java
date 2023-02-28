package com.server.fitnessgym.model.dto;

public class PaymentsDayRequest {
	
	private Long user;
	
	private String paymentMethod;
	
	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

}