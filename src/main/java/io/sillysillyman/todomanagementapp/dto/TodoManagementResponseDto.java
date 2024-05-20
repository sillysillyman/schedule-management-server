package io.sillysillyman.todomanagementapp.dto;

import io.sillysillyman.todomanagementapp.entity.Todo;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoManagementResponseDto {

    private Long todoId;
    private String title;
    private String content;
    private String userName;
    private LocalDateTime createdAt;

    public TodoManagementResponseDto(Todo todo) {
        this.todoId = todo.getTodoId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.userName = todo.getUserName();
        this.createdAt = todo.getCreatedAt();
    }
}
