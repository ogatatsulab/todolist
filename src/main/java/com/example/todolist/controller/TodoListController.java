package com.example.todolist.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.todolist.entity.Todo;
import com.example.todolist.form.TodoForm;
import com.example.todolist.repository.TodoRepository;
import com.example.todolist.service.TodoService;

@Controller
public class TodoListController {
  
  private final TodoRepository todoRepository;
  private final TodoService todoService;
  
  public TodoListController( TodoRepository todoRepository, TodoService todoService ) {
    this.todoRepository = todoRepository;
    this.todoService = todoService;
  }
  
  @GetMapping( "/todo" )
  public String showTodoList( Model model ) {
    List<Todo> todoList = todoRepository.findAll();
    model.addAttribute( "todoList", todoList );
    return "todoList";
  }
  
  @GetMapping( "/todo/create" )
  public String String( Model model ) {
    model.addAttribute( "todoForm", new TodoForm() );
    return "todoForm";
  }
  
  @PostMapping( "/todo/create" )
  public String createTodo( 
      @ModelAttribute @Validated TodoForm todoForm,
      BindingResult result,
      Model model ) {
    
    boolean isValid = todoService.isValid( todoForm, result );
    if ( !result.hasErrors() && isValid ) {
      Todo todo = todoForm.toEntity();
      todoRepository.saveAndFlush( todo );
      return showTodoList( model );
    } else {
      return "todoForm";
    }
    
  }
  
  @PostMapping( "/todo/cancel" )
  public String cancel() {
    return "redirect:/todo";
  }

}
