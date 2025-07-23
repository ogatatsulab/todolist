package com.example.todolist.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.todolist.entity.Todo;
import com.example.todolist.form.TodoForm;
import com.example.todolist.ripository.TodoRepository;
import com.example.todolist.service.TodoService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class TodoListController {
  
  private final TodoRepository todoRepository;
  private final TodoService todoService;
  
/*
  public TodoListController( TodoRepository todoRepository, TodoService todoService ) {
    this.todoRepository = todoRepository;
    this.todoService = todoService;
  }
*/
  
  @GetMapping( "/todo" )
  public ModelAndView showTodoList( ModelAndView mv ) {
    
    mv.setViewName( "todoList" );
    List<Todo> todoList = todoRepository.findAll();
    mv.addObject( "todoList", todoList );
    return mv;
    
  }
  
  @GetMapping( "/todo/create" )
  public ModelAndView createTodo( ModelAndView mv ) {
    mv.setViewName( "todoForm" );
    mv.addObject( "todoForm", new TodoForm() );
    return mv;
  }
  
  @PostMapping( "/todo/create" )
  public ModelAndView createTodo(
      @ModelAttribute @Validated TodoForm todoForm,
      BindingResult result, ModelAndView mv ) {
    boolean isValid = todoService.isValid( todoForm, result );
    if ( !result.hasErrors() && isValid ) {
      // NoErrors
      Todo todo = todoForm.toEntity();
      todoRepository.saveAndFlush( todo );
      return showTodoList( mv );
    } else {
      // AnyErrors
      mv.setViewName( "todoForm" );
      mv.addObject( "todoForm", todoForm );
      return mv;
    }
    
  }
  
  @PostMapping( "/todo/cancel" )
  public String cancel() {
    return "redirect:/todo";
  }

}
