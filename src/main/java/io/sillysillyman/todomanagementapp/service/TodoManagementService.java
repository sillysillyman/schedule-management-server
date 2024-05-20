package io.sillysillyman.todomanagementapp.service;

import io.sillysillyman.todomanagementapp.dto.TodoManagementRequestDto;
import io.sillysillyman.todomanagementapp.entity.Todo;
import io.sillysillyman.todomanagementapp.repository.TodoManagementRepository;
import java.util.List;
import lombok.AllArgsConstructor;
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
        return todoManagementRepository.findById(todoId).orElseThrow(IllegalArgumentException::new);
    }

    public List<Todo> getTodos() {
        return todoManagementRepository.findAll();
    }
}
