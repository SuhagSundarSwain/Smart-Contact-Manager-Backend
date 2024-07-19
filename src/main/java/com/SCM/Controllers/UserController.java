package com.SCM.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping("/dashboard")
    public String getMethodName() {
        return "user/dashboard";
    }
}
