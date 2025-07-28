package com.example.todolist.controller;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolist.entity.Todo;
import com.example.todolist.repository.TodoRepository;



@RestController
public class JpaController {
  
  private final TodoRepository todoRepository;
  
  public JpaController( TodoRepository todoRepository ) {
   this.todoRepository = todoRepository;
  }
  
  
  
  @GetMapping( "/jpaGet" )
  public List<Todo> showTodoJson() {
    return todoRepository.findAll( Sort.by(Sort.Direction.ASC, "id") );
  }

}
