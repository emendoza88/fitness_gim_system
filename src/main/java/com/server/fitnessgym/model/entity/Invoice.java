package com.server.fitnessgym.model.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.server.fitnessgym.model.enumerated.ETypeConcept;

@Entity
@Table(name = "invoice")
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer user;
	
	@NotNull
	@Column(name = "type_concept")
	@Enumerated(EnumType.STRING)
	private ETypeConcept typeConcept;

	@NotNull
	@Column(name = "concept")
	private String concept;
	
	@Column(name = "concept_ref_id")
	private Long conceptRefId;

	@NotNull
	@Column(name = "value")
	private Long value;

	@Column(name = "state")
	private String state;

	@NotNull
	@Column(name = "payment_date")
	private Timestamp paymentdDate;

	@NotNull
	@Column(name = "created_date")
	private Timestamp createdDate;

	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<InvoiceDetaill> invoiceDetaills;

	@PrePersist
	public void addCreateDate() {
		setCreatedDate(new Timestamp(System.currentTimeMillis()));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getUser() {
		return user;
	}

	public void setUser(Customer user) {
		this.user = user;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Timestamp getPaymentdDate() {
		return paymentdDate;
	}

	public void setPaymentdDate(Timestamp paymentdDate) {
		this.paymentdDate = paymentdDate;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getConcept() {
		return concept;
	}

	public void setConcept(String concept) {
		this.concept = concept;
	}
	
	public Long getConceptRefId() {
		return conceptRefId;
	}

	public void setConceptRefId(Long conceptRefId) {
		this.conceptRefId = conceptRefId;
	}
	
	public ETypeConcept getTypeConcept() {
		return typeConcept;
	}

	public void setTypeConcept(ETypeConcept typeConcept) {
		this.typeConcept = typeConcept;
	}

	public List<InvoiceDetaill> getInvoiceDetaills() {
		return invoiceDetaills;
	}

	public void setInvoiceDetaills(List<InvoiceDetaill> invoiceDetaills) {
		this.invoiceDetaills = invoiceDetaills;
	}

}