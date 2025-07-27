package com.example.todolist.service;

import org.springframework.validation.BindingResult;

import com.example.todolist.form.TodoForm;

public interface TodoService {
  
  boolean isValid( TodoForm todoData, BindingResult result );

}
