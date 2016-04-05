package org.kelog.todolist.web;

import org.kelog.todolist.model.Todo;
import org.kelog.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@RestController
public class TodoController {
    
    private TodoService service;
    
    @Autowired
    public TodoController(TodoService service) {
        this.service = service;
    }
    
    @RequestMapping(value = "/todoitems", method = RequestMethod.GET)
    public Collection<Todo> list() {
        return service.findAll();
    }
    
    @RequestMapping(value = "/todoitems/{id}", method = RequestMethod.GET)
    public ResponseEntity<Todo> list(@PathVariable int id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/todoitems", method = RequestMethod.POST)
    public ResponseEntity<Todo> create(@RequestBody @Valid TodoItemCreateDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(service.create(dto.description, dto.importance), HttpStatus.CREATED);
        }
    }
    
    @RequestMapping(value = "/todoitems/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Todo> update(@PathVariable int id, @RequestBody @Valid TodoItemUpdateDto dto,
                                       BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(service.update(id, dto.description, dto.importance), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/todoitems/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Todo> delete(@PathVariable int id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @SuppressWarnings("WeakerAccess")
    private static class TodoItemUpdateDto {
        public String description;
        
        public Boolean importance;
    }
    
    private static class TodoItemCreateDto {
        @NotNull
        public String description;
        
        @NotNull
        public Boolean importance;
    }
}

