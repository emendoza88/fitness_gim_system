package com.server.fitnessgym.model.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.fitnessgym.model.dto.ProductDto;
import com.server.fitnessgym.model.service.ProductService;

@Controller
@RequestMapping(path = "/products")
public class ProductController extends GenericController {
	
	private static final String TITLE = "Productos";
	
	private ProductService service;
	
	public ProductController(ProductService service) {
		this.service = service;
	}
	
	@Override
	public String title() {
		return TITLE;
	}

	@Override
	public String pathCreate() {
		return "/products/create";
	}
	
	@Override
	public String create(Map<String, Object> model) {
		model.put("product", new ProductDto());
		return "pages/productCreate";
	}
	
	@Override
	public String list(Map<String, Object> model) {
		List<ProductDto> products = service.findAll().stream().map(ProductMapperData::toDto).collect(Collectors.toList());
		model.put("productList", products);
		return "pages/products";
	}
	
	@PostMapping("/create")
	public String create(ProductDto request) {
		service.create(ProductMapperData.toEntity(request));
		return "redirect:/products";
	}
	
}