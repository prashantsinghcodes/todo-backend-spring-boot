package com.prashantjavaapps.todo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Todo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	
	private String description;
	
	private String creator;
	
	@Column(name = "created_on")
	private LocalDateTime createdOn;
	
	@Column(name = "modified_on")
	private LocalDateTime modifiedOn;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	private String status;
	
}
