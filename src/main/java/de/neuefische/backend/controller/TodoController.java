package de.neuefische.backend.controller;

import de.neuefische.backend.model.Todo;
import de.neuefische.backend.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping
    public List<Todo> getAllTodos() {
        return this.todoService.listTodos();
    }

    @PostMapping
    public Todo addTodo(@RequestBody Todo todo) {
        return this.todoService.addTodo(todo);
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable String id) {
        if (!this.todoService.todoWithIdExists(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return this.todoService.getTodoById(id);
    }

    @PutMapping("/{id}")
    public Todo updateTodoById(@PathVariable String id, @RequestBody Todo todo) {
        if (!this.todoService.todoWithIdExists(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return this.todoService.updateTodoById(id, todo);
    }

    @DeleteMapping("/{id}")
    public void deleteTodoById(@PathVariable String id) {
        if (!this.todoService.todoWithIdExists(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        this.todoService.deleteTodoById(id);
    }
}
