package com.SCM.SuccessResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

    private String userId;

    public LoginResponse(String userId) {
        this.userId = userId;
    }

}
