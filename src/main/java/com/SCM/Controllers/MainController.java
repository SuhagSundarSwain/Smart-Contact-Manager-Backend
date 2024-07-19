package com.SCM.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.SCM.Entities.User;
import com.SCM.ErrorHandlers.ErrorResponse;
import com.SCM.Forms.UserForm;
import com.SCM.Services.Impls.UserServiceImpl;
import jakarta.validation.Valid;

import java.util.Map;

import javax.print.DocFlavor.STRING;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class MainController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/add-user")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody UserForm userForm, BindingResult rBindingResult) {
        if (rBindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Validation Error", rBindingResult));
        }
        try {
            // User user = User.builder()
            // .name(userForm.getName())
            // .about(userForm.getAbout())
            // .email(userForm.getEmail())
            // .password(userForm.getPassword())
            // .phoneNumber(userForm.getPhoneNumber())
            // .build();

            User user = new User();
            user.setName(userForm.getName());
            user.setAbout(userForm.getAbout());
            user.setEmail(userForm.getEmail());
            user.setPassword(userForm.getPassword());
            user.setPhoneNumber(userForm.getPhoneNumber());

            User savedUser = this.userServiceImpl.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);

        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("UK6dotkott2kjsp8vw4d0m25fb7"))
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("email"));
            else if (e.getMessage().contains("UKdu5v5sr43g5bfnji4vb8hg5s3"))
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("phone"));
            else
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("something"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete-all-user")
    public ResponseEntity<Map<String, String>> deleteAllUser() {
        if (this.userServiceImpl.deleteAllUsers())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Map.of("message", "All cleared"));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Something went wrong"));
    }

}
