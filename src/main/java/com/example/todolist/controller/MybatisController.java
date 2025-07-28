package com.example.todolist.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolist.entity.Todo;
import com.example.todolist.mapper.TodoMapper;



@RestController
public class MybatisController {
  
  private final TodoMapper todoMapper;
  
  public MybatisController( TodoMapper todoMapper ) {
   this.todoMapper = todoMapper;
  }
  
  
  
  @GetMapping( "/mybatisGet" )
  public List<Todo> getTodoJson() {
    return todoMapper.findAll();
  }

}
