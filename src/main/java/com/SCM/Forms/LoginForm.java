package com.SCM.Forms;

import com.SCM.Helper.AppConstants;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class LoginForm {

    @NotBlank(message = AppConstants.BLANK_EMAIL_USERNAME_MESSAGE)
    private String userName;

    @NotBlank(message = AppConstants.BLANK_PASSWORD_MESSAGE)
    private String password;

}
