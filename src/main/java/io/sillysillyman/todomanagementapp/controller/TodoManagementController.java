package io.sillysillyman.todomanagementapp.controller;

import io.sillysillyman.todomanagementapp.dto.TodoManagementRequestDto;
import io.sillysillyman.todomanagementapp.dto.TodoManagementResponseDto;
import io.sillysillyman.todomanagementapp.entity.Todo;
import io.sillysillyman.todomanagementapp.service.TodoManagementService;
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
public class TodoManagementController {

    public final TodoManagementService todoManagementService;

    @Operation(summary = "Create a new todo")
    @PostMapping
    public ResponseEntity<TodoManagementResponseDto> postTodo(
        @Valid @RequestBody TodoManagementRequestDto dto) {
        Todo todo = todoManagementService.createTodo(dto);
        TodoManagementResponseDto responseDto = new TodoManagementResponseDto(todo);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Get a todo by ID")
    @GetMapping("/{todoId}")
    public ResponseEntity<TodoManagementResponseDto> getTodo(@PathVariable Long todoId) {
        Todo todo = todoManagementService.getTodo(todoId);
        TodoManagementResponseDto responseDto = new TodoManagementResponseDto(todo);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Get all todos")
    @GetMapping
    public ResponseEntity<List<TodoManagementResponseDto>> getTodos() {
        List<Todo> todos = todoManagementService.getTodos();
        List<TodoManagementResponseDto> responseDtos = todos.stream()
            .map(TodoManagementResponseDto::new).toList();
        return ResponseEntity.ok().body(responseDtos);
    }

    @Operation(summary = "Update a todo by ID")
    @PutMapping("/{todoId}")
    public ResponseEntity<TodoManagementResponseDto> putTodo(@PathVariable Long todoId,
        @Valid @RequestBody TodoManagementRequestDto requestDto) {
        Todo todo = todoManagementService.updateTodo(todoId, requestDto);
        TodoManagementResponseDto responseDto = new TodoManagementResponseDto(todo);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Delete a todo by ID")
    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long todoId,
        @Valid @RequestBody TodoManagementRequestDto requestDto) {
        todoManagementService.deleteTodo(todoId, requestDto.getPassword());
        return ResponseEntity.ok().build();
    }
}
