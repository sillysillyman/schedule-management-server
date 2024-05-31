package io.sillysillyman.todomanagementapp.service;

import io.sillysillyman.todomanagementapp.dto.TodoRequestDto;
import io.sillysillyman.todomanagementapp.entity.Todo;
import io.sillysillyman.todomanagementapp.exception.PasswordMismatchException;
import io.sillysillyman.todomanagementapp.exception.TodoNotFoundException;
import io.sillysillyman.todomanagementapp.repository.TodoRepository;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public Todo createTodo(TodoRequestDto dto) {
        Todo todo = dto.toEntity();
        return todoRepository.save(todo);
    }

    public Todo getTodo(Long todoId) {
        return todoRepository.findById(todoId)
            .orElseThrow(() -> new TodoNotFoundException("Todo not found with id " + todoId));
    }

    public List<Todo> getTodos() {
        return todoRepository.findAll(Sort.by("createdAt").descending());
    }

    private Todo validatePasswordAndGetTodo(Long todoId, String password) {
        Todo todo = getTodo(todoId);

        if (todo.getPassword() != null && !Objects.equals(todo.getPassword(), password)) {
            throw new PasswordMismatchException("Password mismatch for todo with id " + todoId);
        }

        return todo;
    }

    public Todo updateTodo(Long todoId, TodoRequestDto requestDto) {
        Todo todo = validatePasswordAndGetTodo(todoId, requestDto.getPassword());

        todo.setTitle(requestDto.getTitle());
        todo.setContent(requestDto.getContent());
        todo.setUserId(requestDto.getUserId());
        return todoRepository.save(todo);
    }

    public void deleteTodo(Long todoId, String password) {
        Todo todo = validatePasswordAndGetTodo(todoId, password);
        todoRepository.delete(todo);
    }
}
