package com.server.fitnessgym.model.dto;

public class ProductDto {
	
	private Long id;

	private String code;
	
	private String name;
	
	private Long value;
	
	private Integer count;
	
	private Integer state;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	public String toString(){
		return new StringBuilder()
			.append("name: ") //
			.append(name) //
			.append(", ") //
			.append("value: ") //
			.append(value) //
			.toString();
	}
}