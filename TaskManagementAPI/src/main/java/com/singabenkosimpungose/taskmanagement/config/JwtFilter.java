package com.singabenkosimpungose.taskmanagement.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.singabenkosimpungose.taskmanagement.services.AppUserDetailsService;
import com.singabenkosimpungose.taskmanagement.services.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
This class is a security filter that checks every coming HTTP request to your app. It:
1. Looks for a JWT token
2. Validates that token to make sure it is correct and not expired.
3. Tell Spring Security who the user us
 */

@Component
public class JwtFilter extends OncePerRequestFilter{

    @Autowired
    private JwtService jwtService;  //Hnadles JWT token xreation, exteaction and validation

    @Autowired
    private ApplicationContext context;  //Allow getting other beans managed by Spring.


    /*
     Main method that runs for every HTTP request. Where requests are inspected before reaching controllers
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
       String authorizationHeader =  request.getHeader("Authorization");
       String token = null;
        String username = null;

         if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            token = authorizationHeader.substring(7);
            username = jwtService.extractUsername(token);
            System.out.println("Verifieeeeeed");
        }


        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            System.out.println("Adding userdeatails to centext");
            UserDetails userDetails = context.getBean(AppUserDetailsService.class).loadUserByUsername(username);

            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }

}
