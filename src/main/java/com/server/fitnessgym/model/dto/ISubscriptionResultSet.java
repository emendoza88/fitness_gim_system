package com.server.fitnessgym.model.dto;

import java.sql.Date;

public interface ISubscriptionResultSet {
	
	public Long getUserId();

	public String getName();

	public String getSurname();

	public String getUsername();

	public String getEmail();

	public String getPhone();
	
	public String getNameMembership();
	
	public Long getIdSubscription();
	
	public Long getPriceSubscription();

	public Integer getState();

	public Date getStartDate();

	public Date getExpirationDate();

}