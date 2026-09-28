package com.example.Military_Asset_Management.Configuration;

import com.example.Military_Asset_Management.Services.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println(
                "JWT FILTER: " +
                        request.getMethod() +
                        " " +
                        request.getRequestURI()
        );

        String authHeader = request.getHeader("Authorization");

        String token = null;
        String username = null;

        // 1. Get JWT from Authorization header
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
        }

        // 2. Check that user is not already authenticated
        if (username != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            // 3. Load user from database
            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(username);

            // 4. Validate JWT
            if (jwtService.validateToken(username, userDetails, token)) {

                // 5. Create authenticated object
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                // 6. Put authentication into SecurityContext
                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            }
        }

        System.out.println("JWT FILTER: BEFORE CONTROLLER");
        // 7. Continue request
        filterChain.doFilter(request, response);
        System.out.println("JWT FILTER: AFTER CONTROLLER");
    }
}