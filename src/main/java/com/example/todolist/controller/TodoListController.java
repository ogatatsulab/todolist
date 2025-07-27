package com.example.todolist.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.todolist.entity.Todo;
import com.example.todolist.repository.TodoRepository;

@Controller
public class TodoListController {
  
  private final TodoRepository todoRepository;
  
  public TodoListController( TodoRepository todoRepository ) {
    this.todoRepository = todoRepository;
  }
  
  @GetMapping( "/todo" )
  public String showTodoList( Model model ) {
    List<Todo> todoList = todoRepository.findAll();
    model.addAttribute( "todoList", todoList );
    return "todoList";
  }

}
