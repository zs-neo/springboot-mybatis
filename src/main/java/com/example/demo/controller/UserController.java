package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/go")
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping("/hello")
  public String hello() {
    return "Hello Spring Boot!";
  }

  @RequestMapping("/all")
  public User getAll(){
    return userService.getAll();
  }

}
