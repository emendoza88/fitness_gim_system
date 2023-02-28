package com.server.fitnessgym.model.dto;

import java.util.Date;

public class RegistryCustomerDto {
	private Long user;
	
	private Date date;

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}