package com.SCM.Forms;

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

    @NotBlank(message = "Please enter your name.")
    private String name;

    @NotBlank(message = "Please enter your email.")
    @Email(message = "Enter a valid email.")
    private String email;

    @Size(min = 6, message = "Password should be minimum of 6 characters.")
    private String password;

    @NotBlank(message = "Please enter the about yourself.")
    private String about;

    @Size(min = 10, max = 12, message = "Please enter phone number of minimum 10 digit.")
    private String phoneNumber;

}
