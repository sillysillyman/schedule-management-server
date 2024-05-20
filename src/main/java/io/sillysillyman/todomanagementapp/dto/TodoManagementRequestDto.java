package io.sillysillyman.todomanagementapp.dto;

import io.sillysillyman.todomanagementapp.entity.Todo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoManagementRequestDto {

    private String title;
    private String content;
    private String userName;
    private String password;

    public Todo toEntity() {
        return Todo.builder().title(title).content(content).userName(userName).password(password)
            .build();
    }
}
