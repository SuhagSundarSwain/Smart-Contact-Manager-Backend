package com.SCM.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.SCM.Entities.User;
import com.SCM.ErrorResponse.ErrorResponse;
import com.SCM.Forms.LoginForm;
import com.SCM.Forms.UserForm;
import com.SCM.Helper.AppConstants;
import com.SCM.Services.Impls.UserServiceImpl;
import com.SCM.SuccessResponse.LoginResponse;
import com.SCM.Utils.JwtUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AuthController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Handles user registration.
     * 
     * @param userForm       the user form containing registration details
     * @param rBindingResult the binding result for validation
     * @return a response entity with the created user or an error message
     */
    @PostMapping("/add-user")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody UserForm userForm, BindingResult rBindingResult) {
        if (rBindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new ErrorResponse(AppConstants.VALIDATION_ERROR, rBindingResult));
        }
        try {

            User user = new User();
            user.setName(userForm.getName());
            user.setAbout(userForm.getAbout());
            user.setEmail(userForm.getEmail());
            user.setPassword(userForm.getPassword());
            user.setPhoneNumber(userForm.getPhoneNumber());

            User savedUser = this.userServiceImpl.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);

        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains(AppConstants.DUPLICATE_EMAIL_ERROR_CODE))
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new ErrorResponse(AppConstants.DUPLICATE_ERROR,
                                AppConstants.DUPLICATE_ERROR_FIELD_EMAIL));
            else if (e.getMessage().contains(AppConstants.DUPLICATE_PASSWORD_ERROR_CODE))
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new ErrorResponse(AppConstants.DUPLICATE_ERROR,
                                AppConstants.DUPLICATE_ERROR_FIELD_PHONE));
            else
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new ErrorResponse(AppConstants.DUPLICATE_ERROR,
                                AppConstants.DUPLICATE_ERROR_FIELD_SOMETHING));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Handles user login.
     * 
     * @param loginForm the login form containing username and password
     * @return a response entity with the user ID upon successful authentication
     */
    @PostMapping("/login")
    public ResponseEntity<Object> userLogin(@Valid @RequestBody LoginForm loginForm, BindingResult rBindingResult,HttpServletResponse response) {
        if (rBindingResult.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(AppConstants.VALIDATION_ERROR, rBindingResult));

        try {

            User user = this.userServiceImpl.getLogin(loginForm);

            String jwtToken = jwtUtil.generateToken(user.getUsername());

            Cookie cookie = new Cookie("JWT",jwtToken);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(60*60);

            response.addCookie(cookie);

            return ResponseEntity.status(HttpStatus.OK).body(new LoginResponse(user.getUsername(), jwtToken));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse(AppConstants.INVALID_CREDENTIAL, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    /**
     * Deletes all users from the database.
     * 
     * @return a response entity with a success or error message
     */
    @DeleteMapping("/delete-all-user")
    public ResponseEntity<Map<String, String>> deleteAllUser() {
        if (this.userServiceImpl.deleteAllUsers())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Map.of("message", "All cleared"));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Something went wrong"));
    }
}
