package com.server.fitnessgym.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.fitnessgym.model.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Long> {
	
	Product findByCode(String code);

}
