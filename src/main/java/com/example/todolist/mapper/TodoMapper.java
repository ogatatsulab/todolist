package com.example.todolist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.todolist.entity.Todo;

@Mapper
public interface TodoMapper {
  @Select( "select id, title, importance, urgency, deadline, done from todo order by id" )
  List<Todo> findAll();
}
