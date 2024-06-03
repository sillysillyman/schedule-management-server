package io.sillysillyman.todomanagementapp.dto;

import io.sillysillyman.todomanagementapp.entity.Todo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class TodoRequest {

    @NotBlank(message = "제목은 필수 값입니다.")
    @Size(max = 200, message = "제목은 최대 200자 이내로 입력해주세요.")
    private String title;

    @NotBlank(message = "내용은 필수 값입니다.")
    private String content;

    @NotBlank(message = "사용자 이름은 필수 값입니다.")
    @Email(message = "사용자 이름은 유효한 이메일 형식이어야 합니다.")
    private String userName;

    @NotBlank(message = "비밀번호는 필수 값입니다.")
    private String password;

    public Todo toEntity() {
        return Todo.builder().title(title).content(content).userName(userName).password(password)
            .build();
    }
}
