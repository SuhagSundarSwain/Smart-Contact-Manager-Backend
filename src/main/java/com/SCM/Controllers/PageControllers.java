package com.SCM.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class PageControllers {

    @RequestMapping("/home")
    public String getMethodName() {
        System.out.println("pinged...............");
        return "home";
    }
    

}
