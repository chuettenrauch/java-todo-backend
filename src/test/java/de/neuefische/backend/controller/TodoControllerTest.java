package de.neuefische.backend.controller;

import de.neuefische.backend.model.Status;
import de.neuefische.backend.model.Todo;
import de.neuefische.backend.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private TodoRepository todoRepository;

    @Test
    @DirtiesContext
    void getAllTodos() throws Exception {
        // given
        List<Todo> todos = new ArrayList<>(
                List.of(
                        new Todo("Do something", Status.OPEN),
                        new Todo("Do something else", Status.OPEN)
                )
        );

        this.todoRepository.setTodos(todos);

        String expectedJson = """
                        [
                            {
                                "description": "Do something",
                                "status": "OPEN"
                            },
                            {
                                "description": "Do something else",
                                "status": "OPEN"
                            }
                        ]
                """;

        // when + then
        this.mvc.perform(get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    @DirtiesContext
    void addTodo() throws Exception {
        // given
        String content = """
                {
                    "description": "Abwaschen",
                    "status": "OPEN"
                }
                """;

        MockHttpServletRequestBuilder post = post("/api/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // when + then
        this.mvc.perform(post)
                .andExpect(status().isOk())
                .andExpect(content().json(content));
    }
}