package com.server.fitnessgym.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

@Entity
@Table(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private Long cc;
	
	@NotEmpty
	@Column(name = "name")
	private String name;

	@NotEmpty
	@Column(name = "surname")
	private String surname;

	@NotEmpty
	@Column(name = "username")
	private String username;

	@NotEmpty
	@Column(name = "password")
	private String password;

	@Email
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="role_id")
	private Role role;
	
	@NotNull
	@Column(name = "created_date")
	private Timestamp createdDate;
	
	@Transient
	private String memberships;
	
	private Long supscription;
	
	@PrePersist
    public void addCreateDate() {
        setCreatedDate(new Timestamp(System.currentTimeMillis()));
    }
	
	public String getMemberships() {
		return memberships;
	}

	public void setMemberships(String membership) {
		this.memberships = membership;
	}

	public Customer() {
	}
	
	public Customer(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCc() {
		return cc;
	}

	public void setCc(Long cc) {
		this.cc = cc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String geSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getSurname() {
		return surname;
	}
	
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	
	public Long getSupscription() {
		return supscription;
	}

	public void setSupscription(Long supscription) {
		this.supscription = supscription;
	}

	public String toString() {
		return new StringBuilder().//
				append("{name: ").append(name)//
				.append("surname: ").append(surname)//
				.append("username: ").append(username)//
				.append("cc: ").append(cc)//
				.append("}")
				.toString();
	}
}