package com.example.Military_Asset_Management.Services;

import com.example.Military_Asset_Management.DTOs.SignupReqDto;
import com.example.Military_Asset_Management.DTOs.SignupResDto;
import com.example.Military_Asset_Management.Entities.Base;
import com.example.Military_Asset_Management.Entities.Role;
import com.example.Military_Asset_Management.Entities.User;
import com.example.Military_Asset_Management.Repositories.BaseRepo;
import com.example.Military_Asset_Management.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
   private BaseRepo baseRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public SignupResDto saveUser(SignupReqDto signupReqDto){
        User user=new User();
        user.setUserName(signupReqDto.getName());
        user.setUserEmail(signupReqDto.getEmail());
        user.setPassword(passwordEncoder.encode(signupReqDto.getPassword()));
        Base base=baseRepo.findById(signupReqDto.getBaseId()).orElseThrow(() -> new RuntimeException("Base not found"));;
        user.setBase(base);
        user.setRole(Role.LOGISTICS_OFFICER);
        userRepo.save(user);

        SignupResDto signupDto=new SignupResDto();
        signupDto.setName(user.getUserName());
        signupDto.setEmail(user.getUserEmail());

        return signupDto;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserEmail(username);
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}
