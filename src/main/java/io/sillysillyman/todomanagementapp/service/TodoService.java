package io.sillysillyman.todomanagementapp.service;

import io.sillysillyman.todomanagementapp.dto.TodoRequest;
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

    public Todo createTodo(TodoRequest request) {
        Todo todo = request.toEntity();
        return todoRepository.save(todo);
    }

    public Todo readTodo(Long todoId) {
        return todoRepository.findById(todoId)
            .orElseThrow(() -> new TodoNotFoundException("Todo not found with id " + todoId));
    }

    public List<Todo> readTodos() {
        return todoRepository.findAll(Sort.by("createdAt").descending());
    }

    public Todo updateTodo(Long todoId, TodoRequest request) {
        Todo todo = validatePasswordAndGetTodo(todoId, request.getPassword());

        todo.setTitle(request.getTitle());
        todo.setContent(request.getContent());
        todo.setUserName(request.getUserName());
        return todoRepository.save(todo);
    }

    public void deleteTodo(Long todoId, String password) {
        Todo todo = validatePasswordAndGetTodo(todoId, password);
        todoRepository.delete(todo);
    }

    private Todo validatePasswordAndGetTodo(Long todoId, String password) {
        Todo todo = readTodo(todoId);

        if (todo.getPassword() != null && !Objects.equals(todo.getPassword(), password)) {
            throw new PasswordMismatchException("Password mismatch for todo with id " + todoId);
        }

        return todo;
    }
}
