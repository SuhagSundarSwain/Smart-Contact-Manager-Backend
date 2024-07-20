package com.SCM.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class PageControllers {

    @GetMapping("/")
    public String getRootPath() {
        return "redirect:/home";
    }
    

    @RequestMapping("/home")
    public String getHome() {
        System.out.println("pinged...............");
        return "home";
    }
    

}
