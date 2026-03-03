package com.ecommerce.Ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ErrorController {

    // Access Denied Page (403)
    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }

    // Generic Error Page 
    @GetMapping("/error")
    public String handleError() {
        return "error";
    }
}