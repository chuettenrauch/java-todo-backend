package de.neuefische.backend.service;

import de.neuefische.backend.model.Status;
import de.neuefische.backend.model.Todo;
import de.neuefische.backend.repository.TodoRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TodoServiceTest {

    @Test
    void listTodos_delegatesToRepository() {
        // given
        List<Todo> todos = List.of(
                new Todo("123", "Do something", Status.OPEN),
                new Todo("234", "Do something else", Status.OPEN)
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

    @Test
    void getTodoById_delegatesToRepository() {
        // given
        Todo expected = new Todo("999", "Whatever", Status.OPEN);

        TodoRepository todoRepository = mock(TodoRepository.class);
        when(todoRepository.getTodoById(expected.getId())).thenReturn(expected);

        // when
        TodoService sut = new TodoService(todoRepository);
        Todo actual = sut.getTodoById(expected.getId());

        // then
        assertEquals(expected, actual);

        verify(todoRepository).getTodoById(expected.getId());
    }

    @Test
    void updateTodoById_updatesIdInTodoAndDelegatesToRepository() {
        // given
        Todo given = new Todo("111", "Whatever", Status.IN_PROGRESS);
        Todo expected = new Todo("999", given.getDescription(), given.getStatus());

        TodoRepository todoRepository = mock(TodoRepository.class);
        when(todoRepository.addTodo(expected)).thenReturn(expected);

        // when
        TodoService sut = new TodoService(todoRepository);
        Todo actual = sut.updateTodoById(expected.getId(), given);

        // then
        assertEquals(expected, actual);

        verify(todoRepository).addTodo(expected);
    }

    @Test
    void deleteTodoById_delegatesToRepository() {
        // given
        Todo toBeDeleted = new Todo("111", "Whatever", Status.IN_PROGRESS);

        TodoRepository todoRepository = mock(TodoRepository.class);
        when(todoRepository.deleteTodoById(toBeDeleted.getId())).thenReturn(toBeDeleted);


        // when
        TodoService sut = new TodoService(todoRepository);
        Todo actual = sut.deleteTodoById(toBeDeleted.getId());

        // then
        assertEquals(toBeDeleted, actual);

        verify(todoRepository).deleteTodoById(toBeDeleted.getId());
    }

    @Test
    void todoWithIdExists_returnsTrueIfTodoExistsInRepo() {
        // given
        String id = "123";

        TodoRepository todoRepository = mock(TodoRepository.class);
        when(todoRepository.containsId(id)).thenReturn(true);


        // when
        TodoService sut = new TodoService(todoRepository);
        boolean actual = sut.todoWithIdExists(id);

        // then
        assertTrue(actual);

        verify(todoRepository).containsId(id);
    }

    @Test
    void todoWithIdExists_returnsFalsefTodoDoesNotExistsInRepo() {
        // given
        String id = "123";

        TodoRepository todoRepository = mock(TodoRepository.class);
        when(todoRepository.containsId(id)).thenReturn(false);


        // when
        TodoService sut = new TodoService(todoRepository);
        boolean actual = sut.todoWithIdExists(id);

        // then
        assertFalse(actual);

        verify(todoRepository).containsId(id);
    }
}