package com.prashantjavaapps.todo.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TodoUpdateRequestDto {
	
	private int id;
	
	private String name;
	
	private String description;
	
	private String creator;
	
	private LocalDateTime createdOn;
	
	private String modifiedBy;
	
	private LocalDateTime modifiedOn;
	
	private String status;
	
}
