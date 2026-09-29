package com.example.Military_Asset_Management.Contollers;

import com.example.Military_Asset_Management.DTOs.LoginResDto;
import com.example.Military_Asset_Management.DTOs.SignupReqDto;
import com.example.Military_Asset_Management.DTOs.SignupResDto;
import com.example.Military_Asset_Management.Entities.Admin;
import com.example.Military_Asset_Management.Entities.Base;
import com.example.Military_Asset_Management.Entities.User;
import com.example.Military_Asset_Management.Repositories.AdminRepo;
import com.example.Military_Asset_Management.Repositories.BaseRepo;
import com.example.Military_Asset_Management.Repositories.UserRepo;
import com.example.Military_Asset_Management.Services.JWTService;
import com.example.Military_Asset_Management.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://militaryassetmanagementfrontend.vercel.app/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BaseRepo baseRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AdminRepo adminRepo;

    @PostMapping("/logistic_officer/signup")
    public SignupResDto saveUser(@RequestBody SignupReqDto reqDto){
        return userService.saveUser(reqDto);
    }

//    @PostMapping("/login")
//    public String loginUser(@RequestBody LoginResDto loginResDto) {
//
//        System.out.println("LOGIN CONTROLLER CALLED");
//
//        try {
//
//            Authentication authentication =
//                    authenticationManager.authenticate(
//                            new UsernamePasswordAuthenticationToken(
//                                    loginResDto.getEmail(),
//                                    loginResDto.getPassword()
//                            )
//                    );
//
//            System.out.println("AUTHENTICATION SUCCESSFUL");
//            System.out.println("USER: " + authentication.getName());
//            System.out.println("AUTHORITIES: " + authentication.getAuthorities());
//
//            String email = authentication.getName();
//
//            String role = authentication.getAuthorities()
//                    .stream()
//                    .findFirst()
//                    .get()
//                    .getAuthority()
//                    .replace("ROLE_", "");
//
//            System.out.println("ROLE: " + role);
//
//            String token = jwtService.generateToken(email, role);
//
//            System.out.println("TOKEN GENERATED");
//
//            return token;
//
//        } catch (Exception e) {
//
//            System.out.println("LOGIN ERROR: " + e.getClass().getName());
//            System.out.println("LOGIN ERROR MESSAGE: " + e.getMessage());
//
//            throw e;
//        }
//    }


    @PostMapping("/login")
    public String loginUser(@RequestBody LoginResDto loginResDto) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginResDto.getEmail(),
                                loginResDto.getPassword()
                        )
                );

        String email = authentication.getName();

        String role = authentication.getAuthorities()
                .stream()
                .findFirst()
                .get()
                .getAuthority()
                .replace("ROLE_", "");

        return jwtService.generateToken(email, role);
    }




    @GetMapping("/bases")
    public List<Base> getAllBases() {
        return baseRepo.findAll();
    }
}
