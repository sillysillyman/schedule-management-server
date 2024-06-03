package io.sillysillyman.todomanagementapp.dto;

import io.sillysillyman.todomanagementapp.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;


@Getter
public class RegisterRequest {

    @NotBlank(message = "사용자 이름은 필수 입력 값입니다.")
    @Size(min = 4, max = 10, message = "사용자 이름은 최소 4자 이상, 10자 이하이어야 합니다.")
    @Pattern(regexp = "^[a-z0-9]*$", message = "사용자 이름은 소문자 알파벳(a-z)과 숫자(0-9)로 구성되어야 합니다.")
    private String userName;

    @NotBlank(message = "비밀번호는 필수 입력값 입니다.")
    @Size(min = 8, max = 15, message = "비밀번호는 최소 8자 이상, 15자 이하이어야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "비밀번호는 대소문자 알파벳 (a-z, A-Z)과 숫자(0-9)로 구성되어야 합니다.")
    private String password;

    /* 유효성 검사 불필요 (default로 USER) */
    private String role;

    public User toEntity() {
        return User.builder().userName(userName).password(password).userName(userName).role(role)
            .build();
    }
}
