package com.oocl.training.controller;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class TodoController {
    private Map<Integer, Todo> db = new HashMap<>();
    @PostMapping("/todos")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveTodo(@RequestBody Todo todo) {
        todo.setID(db.size() + 1);
        db.put(db.size() + 1, todo);
    }

    @GetMapping("/todos")
    public List<Todo> getTodos() {
        return new ArrayList<>(db.values());
    }

}
