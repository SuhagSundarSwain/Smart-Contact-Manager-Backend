package com.SCM.Forms;

import com.SCM.Helper.AppConstants;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserForm {

    @NotBlank(message = AppConstants.BLANK_NAME_MESSAGE)
    private String name;

    @NotBlank(message = AppConstants.BLANK_EMAIL_MESSAGE)
    @Email(message = AppConstants.VALID_EMAIL_FORMAT_MESSAGE)
    private String email;

    @Size(min = 6, message = AppConstants.VALID_PASSWORD_SIZE_MESSAGE)
    private String password;

    @NotBlank(message = AppConstants.BLANK_ABOUT_MESSAGE)
    private String about;

    @Size(min = 10, max = 12, message = AppConstants.VALID_PHONE_NUMBER_SIZE_MESSAGE)
    private String phoneNumber;

}
