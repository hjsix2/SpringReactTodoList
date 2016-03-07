package org.kelog.todolist.app;

import org.kelog.todolist.dao.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    
    @Autowired
    TodoRepository repository;
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
