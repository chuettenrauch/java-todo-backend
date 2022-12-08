package de.neuefische.backend.service;

import de.neuefische.backend.model.Todo;
import de.neuefische.backend.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public List<Todo> listTodos() {
        return this.todoRepository.getTodos();
    }

    public Todo addTodo(Todo todo) {
        return this.todoRepository.addTodo(todo);
    }
}
