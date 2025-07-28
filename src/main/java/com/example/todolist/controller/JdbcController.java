package com.example.todolist.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolist.entity.Todo;
import com.example.todolist.repository.TodoRepositoryJdbc;



@RestController
public class JdbcController {
  
  private final TodoRepositoryJdbc todoRepositoryJdbc;
  
  public JdbcController( TodoRepositoryJdbc todoRepositoryJdbc ) {
   this.todoRepositoryJdbc = todoRepositoryJdbc;
  }
  
  
  
  @GetMapping( "/jdbcGet" )
  public List<Todo> getTodoJson() {
    return todoRepositoryJdbc.findAll();
  }

}
