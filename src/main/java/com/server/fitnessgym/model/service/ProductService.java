package com.server.fitnessgym.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.server.fitnessgym.model.dao.ProductDAO;
import com.server.fitnessgym.model.entity.Product;

@Service
public class ProductService extends GenericService<Product, Long> {
	
	@Autowired
	private ProductDAO dao;

	@Override
	public JpaRepository<Product, Long> getDao() {
		return dao;
	}
	
	public Product findByCode(String code) {
		return dao.findByCode(code);
	}

}