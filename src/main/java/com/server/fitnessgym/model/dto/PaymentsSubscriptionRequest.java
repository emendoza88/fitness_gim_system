package com.server.fitnessgym.model.dto;

public class PaymentsSubscriptionRequest {
	
	private Long userId;
	
	private Long idSubscription;
	
	private String paymentMethod;
	
	public PaymentsSubscriptionRequest() {
	}
	
	public PaymentsSubscriptionRequest(Long userId, Long idSubscription, String paymentMethod) {
		this.userId = userId;
		this.idSubscription = idSubscription;
		this.paymentMethod = paymentMethod;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	public Long getIdSubscription() {
		return idSubscription;
	}

	public void setIdSubscription(Long idSubscription) {
		this.idSubscription = idSubscription;
	}

	public String toString(){
		return new StringBuilder()
			.append("user: ") //
			.append(userId) //
			.append(", ") //
			.append("paymentMethod: ") //
			.append(paymentMethod) //
			.append(", ") //
			.append("idSubscription: ") //
			.append(idSubscription) //
			.toString();
	}

}