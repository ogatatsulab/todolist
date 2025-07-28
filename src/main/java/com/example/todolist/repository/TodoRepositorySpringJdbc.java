package com.example.todolist.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.todolist.entity.Todo;

@Repository
public class TodoRepositorySpringJdbc {
  
  private final JdbcTemplate jdbcTemplate;
  
  public TodoRepositorySpringJdbc( JdbcTemplate jdbcTemplate ) {
    this.jdbcTemplate = jdbcTemplate;
  }
  
  public List<Todo> findAll() {
    
    String sql = "select id, title, importance, urgency, deadline, done from todo order by id";
    
    return jdbcTemplate.query( sql, new RowMapper<Todo>() {
      @Override
      public Todo mapRow( ResultSet rs, int rowNum ) throws SQLException {
        Todo todo = new Todo();
        todo.setId( rs.getInt( "id" ) );
        todo.setTitle( rs.getString( "title" ) );
        todo.setImportance( rs.getInt( "importance" ) );
        todo.setUrgency( rs.getInt( "urgency" ) );
        todo.setDeadline( rs.getDate( "deadline" ) );
        todo.setDone( rs.getString( "done" ) );
        return todo;
      }
    } );
  }

}
