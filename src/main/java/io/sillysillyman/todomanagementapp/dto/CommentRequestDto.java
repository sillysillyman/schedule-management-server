package io.sillysillyman.todomanagementapp.dto;


import io.sillysillyman.todomanagementapp.entity.Comment;
import io.sillysillyman.todomanagementapp.entity.Todo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentRequestDto {

    @NotBlank(message = "댓글 내용은 필수 값입니다.")
    @Size(max = 1000, message = "댓글 내용은 최대 1000자 이내로 입력해주세요.")
    private String content;

    @NotBlank(message = "사용자 아이디는 필수 값입니다.")
    private String userId;

    public Comment toEntity(Todo todo) {
        return Comment.builder()
            .content(this.content)
            .userId(this.userId)
            .todo(todo)
            .build();
    }
}
