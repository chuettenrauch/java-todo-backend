package de.neuefische.backend.service;

import de.neuefische.backend.model.Status;
import de.neuefische.backend.model.Todo;
import de.neuefische.backend.repository.TodoRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TodoServiceTest {

    @Test
    void listTodos_delegatesToRepository() {
        // given
        List<Todo> todos = new ArrayList<>(
                List.of(
                        new Todo("123", "Do something", Status.OPEN),
                        new Todo("234","Do something else", Status.OPEN)
                )
        );

        TodoRepository todoRepository = mock(TodoRepository.class);
        when(todoRepository.getTodos()).thenReturn(todos);

        // when
        TodoService sut = new TodoService(todoRepository);
        List<Todo> actual = sut.listTodos();

        // then
        assertEquals(todos, actual);

        verify(todoRepository).getTodos();
    }

    @Test
    void addTodo_addsRandomIdAndDelegatesToRepository() {
        // given
        Todo todo = new Todo(null, "Do something", Status.OPEN);

        TodoRepository todoRepository = mock(TodoRepository.class);
        when(todoRepository.addTodo(todo)).thenReturn(todo);

        // when
        TodoService sut = new TodoService(todoRepository);
        Todo actual = sut.addTodo(todo);

        // then
        assertEquals(todo, actual);
        assertNotEquals(null, actual.getId());

        verify(todoRepository).addTodo(todo);
    }
}