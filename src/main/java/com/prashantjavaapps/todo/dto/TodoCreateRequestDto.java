package com.prashantjavaapps.todo.dto;

import lombok.Data;

@Data
public class TodoCreateRequestDto {
	
	private String name;
	
	private String description;

	private String status;
	
	private String creator;
	
	private String modifiedBy;
	
}
