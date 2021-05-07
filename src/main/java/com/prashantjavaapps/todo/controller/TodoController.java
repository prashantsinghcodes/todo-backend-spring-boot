package com.prashantjavaapps.todo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prashantjavaapps.todo.dto.LoginRequestDto;
import com.prashantjavaapps.todo.dto.LoginResponseDto;
import com.prashantjavaapps.todo.dto.TodoCreateRequestDto;
import com.prashantjavaapps.todo.dto.TodoUpdateRequestDto;
import com.prashantjavaapps.todo.entity.Todo;
import com.prashantjavaapps.todo.service.TodoService;

@RestController
@RequestMapping("/todo")
@CrossOrigin(origins = "http://localhost:3000")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Todo createTodo(@RequestBody TodoCreateRequestDto requestDto) {
		return todoService.saveTodo(requestDto);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Todo updateTodo(@RequestBody TodoUpdateRequestDto requestDto) {
		return todoService.updateTodo(requestDto);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void deleteTodo(@PathVariable("id") int id) {
		todoService.deleteTodo(id);
	}
	
	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public List<Todo> getAllTodos() {
		return todoService.getAllTodos();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Todo getTodoById(@PathVariable("id") int id) {
		return todoService.getTodoById(id);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<LoginResponseDto> getAuthenticatedOnLogin(@RequestBody LoginRequestDto loginRequest) {
		LoginResponseDto loginResponse = todoService.authenticateUser(loginRequest);
		return ResponseEntity.ok(loginResponse);
	}
}
