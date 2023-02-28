package com.server.fitnessgym.model.dto;

public class ProductItemDto {
	
	private String typeReference;
	
	private Long idReference;
	
	private Long value;
	
	public ProductItemDto() {
	}
	
	public ProductItemDto(String typeReference, Long idReference, Long value) {
		this.typeReference = typeReference;
		this.idReference = idReference;
		this.value = value;
	}
	
	public String getTypeReference() {
		return typeReference;
	}

	public void setTypeReference(String typeReference) {
		this.typeReference = typeReference;
	}

	public Long getIdReference() {
		return idReference;
	}

	public void setIdReference(Long idReference) {
		this.idReference = idReference;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

}