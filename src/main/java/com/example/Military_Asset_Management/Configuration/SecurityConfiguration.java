package com.example.Military_Asset_Management.Configuration;

import com.example.Military_Asset_Management.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private JWTFilter jwtFilter;


    @Bean
    public SecurityFilterChain basicAuth(HttpSecurity http){
        http


                .csrf(csrf -> csrf.disable())
                .cors(withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/logistic_officer/signup", "/login","/get/bases","/base_commander/signup").permitAll()
                        .requestMatchers(
                                "/purchase/**",
                                "/transfer/**"
                        ).hasRole("LOGISTICS_OFFICER")

                        .requestMatchers(
                                "/openBalance/**",
                                "/closingBalance/**",
                                "/netMovement/**",
                                "/save/itemAssignment",
                                "/items/assign",
                                "/save/expenditure",
                                "/expenditure/**"
                        ).hasRole("BASE_COMMANDER")
                        .anyRequest().authenticated()
                )

                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        return new UserService();
//    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,PasswordEncoder passwordEncoder){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager( daoAuthenticationProvider);
    }

}
