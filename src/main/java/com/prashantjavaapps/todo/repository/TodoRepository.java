package com.prashantjavaapps.todo.repository;

import org.springframework.data.repository.CrudRepository;

import com.prashantjavaapps.todo.entity.Todo;

public interface TodoRepository extends CrudRepository<Todo, Integer> {

}
