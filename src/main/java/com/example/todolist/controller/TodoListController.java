package com.example.todolist.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.todolist.entity.Todo;
import com.example.todolist.form.TodoForm;
import com.example.todolist.repository.TodoRepository;
import com.example.todolist.service.TodoService;



@Controller
public class TodoListController {
  
  private final TodoRepository todoRepository;
  private final TodoService todoService;
  private final HttpSession session;
  
  public TodoListController(
      TodoRepository todoRepository,
      TodoService todoService,
      HttpSession session ) {
    this.todoRepository = todoRepository;
    this.todoService = todoService;
    this.session = session;
  }
  
  
  
  @GetMapping( "/todo" )
  public String showTodoList( Model model ) {
    List<Todo> todoList = todoRepository.findAll( Sort.by(Sort.Direction.ASC, "id") );
    model.addAttribute( "todoList", todoList );
    return "todoList";
  }
  
  
  
  @GetMapping( "/todo/{id}" )
  public String todoById( @PathVariable( name = "id" ) int id, Model model ) {
    Todo todo = todoRepository.findById( id ).get();
    model.addAttribute( "todoForm", TodoForm.fromTodo( todo ) );
    session.setAttribute( "mode", "update" );
    return "todoForm";
  }
  
  
  
  @GetMapping( "/todo/create" )
  public String String( Model model ) {
    model.addAttribute( "todoForm", new TodoForm() );
    session.setAttribute( "mode", "create" );
    return "todoForm";
  }
  
  
  
  @PostMapping( "/todo/create" )
  public String createTodo( 
      @ModelAttribute @Validated TodoForm todoForm,
      BindingResult result,
      Model model ) {
    
    boolean isValid = todoService.isValid( todoForm, result );
    if ( !result.hasErrors() && isValid ) {
      Todo todo = todoForm.toTodo();
      todoRepository.saveAndFlush( todo );
      return "redirect:/todo";
    } else {
      return "todoForm";
    }
    
  }
  
  
  
  @PostMapping( "/todo/update" )
  public String updateTodo(
      @ModelAttribute @Validated TodoForm todoForm,
      BindingResult result,
      Model model ) {
    
     
    boolean isValid = todoService.isValid( todoForm, result );
    if ( !result.hasErrors() && isValid ) {
      Todo todo = todoForm.toTodo();
      System.out.println( todo );
      todoRepository.saveAndFlush( todo );
      return "redirect:/todo";
    } else {
      return "todoForm";
    }
    
  }
  
  
  
  @PostMapping( "todo/delete" )
  public String deleteTodo( @ModelAttribute TodoForm todoForm ) {
    todoRepository.deleteById( todoForm.getId() );
    return "redirect:/todo";
  }
  
  
  
  @PostMapping( "/todo/cancel" )
  public String cancel() {
    return "redirect:/todo";
  }

}
