package com.example.todolist.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolist.entity.Todo;
import com.example.todolist.repository.TodoRepositorySpringJdbc;



@RestController
public class SpringJdbcController {
  
  private final TodoRepositorySpringJdbc todoRepositorySpringJdbc;
  
  public SpringJdbcController( TodoRepositorySpringJdbc todoRepositorySpringJdbc ) {
   this.todoRepositorySpringJdbc = todoRepositorySpringJdbc;
  }
  
  
  
  @GetMapping( "/springjdbcGet" )
  public List<Todo> getTodoJson() {
    return todoRepositorySpringJdbc.findAll();
  }

}
