package com.valtech.poc.sms.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;
    //Performing the JWT validation and setting the user authentication context if valid.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");// Get the Authorization header from the request
        // Initialize variables for the username and JWT
        String username = null;
        String jwt = null;
        // If the Authorization header is not null and starts with "Bearer ",
        // extract the JWT and username from the header
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }
        // If the username is not null and there is no current authentication in the SecurityContextHolder
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        	logger.debug("JWT Filter: Validating JWT for user {}", username);
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);// Load the UserDetails object for the username
            // If the JWT is valid for the UserDetails object, set the authentication in the SecurityContextHolder
            if (jwtUtil.validateToken(jwt, userDetails)) {
                logger.debug("JWT Filter: JWT is valid. Setting authentication for user {}", username);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                logger.info("Successfully authenticated user {}", username);
            }
            // If the JWT is not valid for the UserDetails object, log a warning
            logger.warn("JWT Filter: JWT is invalid for user {}", username);
        }
        chain.doFilter(request, response);// Call the next filter in the chain
    }
}


