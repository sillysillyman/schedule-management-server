package io.sillysillyman.todomanagementapp.dto;

import io.sillysillyman.todomanagementapp.entity.User;

public class RegisterResponse {

    private Long userId;
    private String userName;
    private String role;
    private String message;


    public RegisterResponse(User user, String message) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.role = user.getRole();
        this.message = message;
    }
}
