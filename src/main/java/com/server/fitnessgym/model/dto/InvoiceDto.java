package com.server.fitnessgym.model.dto;

import java.util.List;

public class InvoiceDto {
	
	private Long user;
	
	private List<ProductItemDto> productsList;

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	public List<ProductItemDto> getProductsList() {
		return productsList;
	}

	public void setProductsList(List<ProductItemDto> productsList) {
		this.productsList = productsList;
	}

}