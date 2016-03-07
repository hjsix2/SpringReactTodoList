package org.kelog.todolist.web;

import org.kelog.todolist.dao.TodoItemNotFoundException;
import org.kelog.todolist.dao.TodoRepository;
import org.kelog.todolist.model.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@RestController
public class TodoItemController {

    @Autowired
    TodoRepository repository;

    @RequestMapping(value = "/todoitems", method = RequestMethod.GET)
    public Collection<TodoItem> list() {
        return repository.findAll();
    }

    @RequestMapping(value = "/todoitems/{id}", method = RequestMethod.GET)
    public ResponseEntity<TodoItem> list(@PathVariable int id) {
        try {
            return new ResponseEntity<>(repository.findById(id), HttpStatus.OK);
        } catch (TodoItemNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/todoitems", method = RequestMethod.POST)
    public ResponseEntity<TodoItem> create(@RequestBody @Valid TodoItemCreateDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(repository.create(dto.description, dto.importance), HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/todoitems/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TodoItem> update(@PathVariable int id, @RequestBody @Valid TodoItemUpdateDto dto,
                                           BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            return new ResponseEntity<>(repository.update(id, dto.description, dto.importance), HttpStatus.OK);
        } catch (TodoItemNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/todoitems/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<TodoItem> update(@PathVariable int id) {
        try {
            repository.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (TodoItemNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    static class TodoItemUpdateDto {
        @NotNull
        public String description;

        @NotNull
        public TodoItem.Importance importance;
    }

    static class TodoItemCreateDto {
        @NotNull
        public String description;
        
        @NotNull
        public TodoItem.Importance importance;
    }
}

