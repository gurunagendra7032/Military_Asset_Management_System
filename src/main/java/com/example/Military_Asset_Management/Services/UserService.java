package com.example.Military_Asset_Management.Services;

import com.example.Military_Asset_Management.DTOs.SignupReqDto;
import com.example.Military_Asset_Management.DTOs.SignupResDto;
import com.example.Military_Asset_Management.Entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    public SignupResDto saveUser(SignupReqDto signupReqDto){
        User user=new User();
        user.setUserName(signupReqDto.getName());
        user.setUserEmail(signupReqDto.getEmail());
        user.setPassword(signupReqDto.getPassword());

        SignupResDto signupDto=new SignupResDto();
        signupDto.setName(user.getUserName());
        signupDto.setEmail(user.getUserEmail());

        return signupDto;
    }


}
