package com.server.fitnessgym.model.dto;

public class PaymentsProductRequest {
	
	private Long user;
	
	private String listProducts;
	
	private String paymentMethod;

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	public String getListProducts() {
		return listProducts;
	}

	public void setListProducts(String listProducts) {
		this.listProducts = listProducts;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	public String toString(){
		return new StringBuilder()
			.append("user: ") //
			.append(user) //
			.append(", ") //
			.append("paymentMethod: ") //
			.append(paymentMethod) //
			.append(", ") //
			.append("listProducts: ") //
			.append(listProducts) //
			.toString();
	}
	
}