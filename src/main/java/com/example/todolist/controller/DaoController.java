package com.example.todolist.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolist.dao.TodoDao;
import com.example.todolist.entity.Todo;



@RestController
public class DaoController {
  
  private final TodoDao todoDao = new TodoDao();
  
  @GetMapping( "/daoGet" )
  public List<Todo> getTodoJson() {
    return todoDao.findAll();
  }

}
