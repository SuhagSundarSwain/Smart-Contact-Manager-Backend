package com.SCM.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.SCM.Entities.User;
import com.SCM.Forms.UserForm;
import com.SCM.Services.Impls.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class MainController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/add-user")
    public User postMethodName(@RequestBody UserForm userForm) {

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

        System.out.println(savedUser);

        return savedUser;
    }

    @DeleteMapping("/delete-all-user")
    public String deleteAllUser() {
        if (this.userServiceImpl.deleteAllUsers())
            return "Clear....";
        return "Something went wrong";
    }

}
