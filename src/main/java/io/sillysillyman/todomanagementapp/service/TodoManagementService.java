package io.sillysillyman.todomanagementapp.service;

import io.sillysillyman.todomanagementapp.dto.TodoManagementRequestDto;
import io.sillysillyman.todomanagementapp.entity.Todo;
import io.sillysillyman.todomanagementapp.exception.PasswordMismatchException;
import io.sillysillyman.todomanagementapp.exception.TodoNotFoundException;
import io.sillysillyman.todomanagementapp.repository.TodoManagementRepository;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoManagementService {

    private final TodoManagementRepository todoManagementRepository;

    public Todo createTodo(TodoManagementRequestDto dto) {
        Todo todo = dto.toEntity();
        return todoManagementRepository.save(todo);
    }

    public Todo getTodo(Long todoId) {
        return todoManagementRepository.findById(todoId)
            .orElseThrow(() -> new TodoNotFoundException("Todo not found with id " + todoId));
    }

    public List<Todo> getTodos() {
        return todoManagementRepository.findAll(Sort.by("createdAt").descending());
    }

    private Todo validatePasswordAndGetTodo(Long todoId, String password) {
        Todo todo = getTodo(todoId);

        if (todo.getPassword() != null && !Objects.equals(todo.getPassword(), password)) {
            throw new PasswordMismatchException("Password mismatch for todo with id " + todoId);
        }

        return todo;
    }

    public Todo updateTodo(Long todoId, TodoManagementRequestDto requestDto) {
        Todo todo = validatePasswordAndGetTodo(todoId, requestDto.getPassword());

        todo.setTitle(requestDto.getTitle());
        todo.setContent(requestDto.getContent());
        todo.setUserName(requestDto.getUserName());
        return todoManagementRepository.save(todo);
    }

    public void deleteTodo(Long todoId, String password) {
        Todo todo = validatePasswordAndGetTodo(todoId, password);
        todoManagementRepository.delete(todo);
    }
}
