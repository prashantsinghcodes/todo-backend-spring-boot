package com.prashantjavaapps.todo.service;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prashantjavaapps.todo.dto.LoginRequestDto;
import com.prashantjavaapps.todo.dto.LoginResponseDto;
import com.prashantjavaapps.todo.dto.TodoCreateRequestDto;
import com.prashantjavaapps.todo.dto.TodoUpdateRequestDto;
import com.prashantjavaapps.todo.entity.Todo;
import com.prashantjavaapps.todo.repository.TodoRepository;
import com.prashantjavaapps.todo.utils.UtilConstants;
import com.prashantjavaapps.todo.utils.Utility;

@Service
public class TodoService {
	
	Logger logger = LoggerFactory.getLogger(TodoService.class);

	@Autowired
	private TodoRepository todoRepository;
	
	public Todo saveTodo(TodoCreateRequestDto requestDto) {
		if (requestDto.getName() != null && !requestDto.getName().equals("") && requestDto.getDescription() != null
				&& !requestDto.getDescription().equals("") && requestDto.getStatus() != null) {
			Todo todo = new Todo();
			try {
				BeanUtils.copyProperties(todo, requestDto);
				Utility utility = new Utility();
				todo.setCreatedOn(LocalDateTime.now());
				todo.setModifiedOn(todo.getCreatedOn());
				return todoRepository.save(todo);
			} catch (IllegalAccessException | InvocationTargetException e) {
				logger.info("could not copy beans");
			}
		} else {
			logger.info("Can't have name, description and status null or blank");
		}
		return null;
	}
	
	public Todo updateTodo(TodoUpdateRequestDto requestDto) {
		if(requestDto.getId() != 0) {
			Optional<Todo> optionalTodo = todoRepository.findById(requestDto.getId());
			if(!optionalTodo.isEmpty()) {
				Todo todo = optionalTodo.get();
				try {
					BeanUtils.copyProperties(todo, requestDto);
					todo.setModifiedOn(LocalDateTime.now());
					return todoRepository.save(todo);
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public void deleteTodo(int id) {
		if(id != 0) {
		    Optional<Todo> optionalTodo = todoRepository.findById(id);
		    if(!optionalTodo.isEmpty()) {
		    	Todo todo = optionalTodo.get();
		    	todoRepository.deleteById(todo.getId());
		    }else {
		    	logger.info("Todo not found for id: "+ id);
		    }
		}else {
			logger.info("id is zero");
		}
	}
	
	public List<Todo> getAllTodos() {
		return (List<Todo>) todoRepository.findAll();
	}
	
	public Todo getTodoById(int id) {
		if(id != 0) {
			Optional<Todo> optionalTodo = todoRepository.findById(id);
			if(!optionalTodo.isEmpty()) {
				return optionalTodo.get();
			}
		}
		return null;
	}
	
	public LoginResponseDto authenticateUser(LoginRequestDto loginRequest) {
		if (loginRequest != null && loginRequest.getUsername() != null && !loginRequest.getUsername().equals("")
				&& loginRequest.getPassword() != null && !loginRequest.getPassword().equals("")) {
			if (loginRequest.getUsername().equals(UtilConstants.TODO_APP_USERNAME)
					&& loginRequest.getPassword().equals(UtilConstants.TODO_APP_PASSWORD)) {
				LoginResponseDto responseDto = new LoginResponseDto();
				responseDto.setToken(UtilConstants.TODO_APP_TOKEN);
				return responseDto;
			}
			logger.info("Wrong username or password! {}", loginRequest);
		}else {
			logger.info("username or password is blank or null! {}", loginRequest);
		}
		return null;
	}
}
