package io.sillysillyman.todomanagementapp.controller;

import io.sillysillyman.todomanagementapp.dto.TodoRequestDto;
import io.sillysillyman.todomanagementapp.dto.TodoResponseDto;
import io.sillysillyman.todomanagementapp.entity.Todo;
import io.sillysillyman.todomanagementapp.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1.0/todo")
@RestController
@AllArgsConstructor
public class TodoController {

    public final TodoService todoManagementService;

    @Operation(summary = "Create a new todo")
    @PostMapping
    public ResponseEntity<TodoResponseDto> postTodo(
        @Valid @RequestBody TodoRequestDto requestDto) {
        Todo todo = todoManagementService.createTodo(requestDto);
        TodoResponseDto responseDto = new TodoResponseDto(todo);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Get a todo by ID")
    @GetMapping("/{todoId}")
    public ResponseEntity<TodoResponseDto> getTodo(@PathVariable Long todoId) {
        Todo todo = todoManagementService.readTodo(todoId);
        TodoResponseDto responseDto = new TodoResponseDto(todo);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Get all todos")
    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> getTodos() {
        List<Todo> todos = todoManagementService.readTodos();
        List<TodoResponseDto> responseDtos = todos.stream()
            .map(TodoResponseDto::new).toList();
        return ResponseEntity.ok().body(responseDtos);
    }

    @Operation(summary = "Update a todo by ID")
    @PutMapping("/{todoId}")
    public ResponseEntity<TodoResponseDto> putTodo(@PathVariable Long todoId,
        @Valid @RequestBody TodoRequestDto requestDto) {
        Todo todo = todoManagementService.updateTodo(todoId, requestDto);
        TodoResponseDto responseDto = new TodoResponseDto(todo);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Delete a todo by ID")
    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long todoId,
        @Valid @RequestBody TodoRequestDto requestDto) {
        todoManagementService.deleteTodo(todoId, requestDto.getPassword());
        return ResponseEntity.ok().build();
    }
}
