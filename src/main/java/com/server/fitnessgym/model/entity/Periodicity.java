package com.server.fitnessgym.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "periodicity")
public class Periodicity {

	@Id
	private Long id;

	private String name;

	private int countMonth;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCountMonth() {
		return countMonth;
	}

	public void setCountMonth(int countMonth) {
		this.countMonth = countMonth;
	}

}