package com.server.fitnessgym.model.dto;

public class PaymentDto {

	private Long id;

	private String paymentDate;

	private String typeConcept;

	private String concept;

	private String paymentMethod;

	private String value;

	private String name;

	private String surname;

	private String email;

	private String phone;

	public Long getId() {
		return this.id;
	}

	public String getPaymentDate() {
		return this.paymentDate;
	}

	public String getTypeConcept() {
		return typeConcept;
	}

	public void setTypeConcept(String typeConcept) {
		this.typeConcept = typeConcept;
	}

	public String getConcept() {
		return concept;
	}

	public void setConcept(String concept) {
		this.concept = concept;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

}