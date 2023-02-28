package com.server.fitnessgym.model.controller;

import com.server.fitnessgym.model.dto.ProductDto;
import com.server.fitnessgym.model.entity.Product;

public class ProductMapperData {

	public static Product toEntity(ProductDto dto) {
		Product entity = new Product();
		entity.setName(dto.getName());
		entity.setCode(dto.getCode());
		entity.setValue(dto.getValue());
		entity.setCount(dto.getCount());
		entity.setState(dto.getState());
		return entity;
	}
	
	public static ProductDto toDto(Product entity) {
		ProductDto dto = new ProductDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCode(entity.getCode());
		dto.setValue(entity.getValue());
		dto.setCount(entity.getCount());
		dto.setState(entity.getState());
		return dto;
	}
}