package com.SCM.SuccessResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

    private String userId;
    private String jwtToken;

    public LoginResponse(String userId, String jwtToken) {
        this.userId = userId;
        this.jwtToken = jwtToken;
    }

}
