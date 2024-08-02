package com.SCM.Forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
@Builder
@ToString
public class ContactForm {

    @NotBlank(message = "Name is required.")
    private String name;

    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email address.")
    private String email;

    @Pattern(regexp = "^(\\+91[\\-\\s]?)?(\\d{3}[\\-\\s]?\\d{3}[\\-\\s]?\\d{4})$", message = "Enter a valid phone number.")
    private String phoneNumber;

    private String address;
    private String description;
    private boolean favourite;

}
