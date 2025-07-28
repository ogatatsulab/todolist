package com.example.todolist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.todolist.entity.Todo;

public class TodoDao {

    private static final String URL = "jdbc:postgresql://localhost:5432/tododb";
    private static final String USER = "todouser";
    private static final String PASSWORD = "password";
    
    public List<Todo> findAll() {
      
      List<Todo> todoList = new ArrayList<>();

      String sql = "select id, title, importance, urgency, deadline, done from todo order by id";

      try (
        Connection conn = DriverManager.getConnection( URL, USER, PASSWORD );
        PreparedStatement stmt = conn.prepareStatement( sql );
        ResultSet rs = stmt.executeQuery();
      ) {
        while ( rs.next() ) {
          Todo todo = new Todo();
          todo.setId( rs.getInt( "id" ) );
          todo.setTitle( rs.getString( "title" ) );
          todo.setImportance( rs.getInt( "importance" ) );
          todo.setUrgency( rs.getInt( "urgency" ) );
          todo.setDeadline( rs.getDate( "deadline" ) );
          todo.setDone( rs.getString( "done" ) );
          todoList.add( todo );
        }
      } catch( SQLException e ) {
        e.printStackTrace();
      }

      return todoList;
      
    }
    
}
