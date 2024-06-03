package io.sillysillyman.todomanagementapp.dto;


import io.sillysillyman.todomanagementapp.entity.Comment;
import io.sillysillyman.todomanagementapp.entity.Todo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentRequest {

    @NotBlank(message = "댓글 내용은 필수 값입니다.")
    @Size(max = 1000, message = "댓글 내용은 최대 1000자 이내로 입력해주세요.")
    private String content;

    @NotBlank(message = "사용자 이름은 필수 값입니다.")
    private String userName;

    public Comment toEntity(Todo todo) {
        return Comment.builder()
            .content(this.content)
            .userName(this.userName)
            .todo(todo)
            .build();
    }
}
