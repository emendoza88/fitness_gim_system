package com.server.fitnessgym.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.server.fitnessgym.model.enumerated.EPaymentMethod;

@Entity
@Table(name = "payment")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn
	@ManyToOne(fetch = FetchType.LAZY)
	private Invoice invoice;

	@NotNull
	@Column(name = "value")
	private Long value;
	
	@NotNull
	@Column(name = "payment_method")
	@Enumerated(EnumType.STRING)
	private EPaymentMethod paymentMethod;

	@NotNull
	@Column(name = "created_date")
	private Timestamp createdDate;
	
	@PrePersist
	public void addCreateDate() {
		setCreatedDate(new Timestamp(System.currentTimeMillis()));
	}

	@Transient
	private Long user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public EPaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(EPaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

}