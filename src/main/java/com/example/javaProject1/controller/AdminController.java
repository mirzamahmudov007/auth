package com.example.javaProject1.controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class AdminController {


    @GetMapping("/admin/test")
    public String test(){
        return "Hello Admin:)";
    }



}
