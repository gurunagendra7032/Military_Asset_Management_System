package com.example.Military_Asset_Management.Contollers;

import com.example.Military_Asset_Management.DTOs.LoginResDto;
import com.example.Military_Asset_Management.DTOs.SignupReqDto;
import com.example.Military_Asset_Management.DTOs.SignupResDto;
import com.example.Military_Asset_Management.Entities.User;
import com.example.Military_Asset_Management.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save/user")
    public SignupResDto saveUser(@RequestBody SignupReqDto reqDto){
       return userService.saveUser(reqDto);
    }


    public String loginUser(@RequestBody LoginResDto loginResDto){
        User user=new User();
        user.setUserEmail(loginResDto.getEmail());
        user.setPassword(loginResDto.getPassword());

        return "Login Successfully";
    }
}
