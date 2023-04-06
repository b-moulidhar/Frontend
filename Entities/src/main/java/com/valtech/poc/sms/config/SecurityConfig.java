package com.valtech.poc.sms.config;

import java.util.Arrays; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.valtech.poc.sms.security.JwtFilter;



@SuppressWarnings("deprecation")// Suppress deprecation warnings
@Configuration// This class is a Spring configuration class
@EnableWebSecurity// Enable Spring Security for the application
@EnableGlobalMethodSecurity(prePostEnabled = true)// Enable method-level security for the application
public class SecurityConfig {
	
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    // Inject the UserDetailsService that will be used to load user details for authentication
    @Autowired
    private UserDetailsService userDetailsService;

    // Inject the JwtFilter that will be used to filter requests and authenticate users
    @Autowired
    private JwtFilter jwtFilter;

    // Define a bean for the AuthenticationManager that will be used to authenticate users
    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
    	logger.info("Creating authentication manager bean");
        return configuration.getAuthenticationManager();//Get the authentication manager from the authentication configuration and return it
    }
    // Define a bean for the BCryptPasswordEncoder that will be used to encode passwords
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        logger.info("Creating BCrypt password encoder bean");
    	return new BCryptPasswordEncoder();
    }
    // Define a bean for the DaoAuthenticationProvider that will be used to authenticate users with a custom UserDetailsService and password encoder
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        logger.info("Creating Dao authentication provider bean");    // Log a message that the Dao authentication provider bean is being created
    	DaoAuthenticationProvider provider=new DaoAuthenticationProvider();//Create a new instance of DaoAuthenticationProvider
    	provider.setUserDetailsService(userDetailsService);// Set the user details service that will be used to load user-specific data and the user details service must implement the UserDetailsService interface
    	provider.setPasswordEncoder(bCryptPasswordEncoder());// Set the password encoder that will be used to encode and verify passwords in this case, BCryptPasswordEncoder is used
    	return provider;// Return the Dao authentication provider bean
    	
    }
    // Define a bean for the SecurityFilterChain that will be used to define the security configuration for the application
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        logger.info("Configuring security filter chain");
        // Configure the HttpSecurity object to specify the security rules for the application
    	http
        .csrf()
        .disable()// Disables CSRF protection
        .cors().and() // Enables CORS
                .authorizeHttpRequests()
                .requestMatchers("/api/login","/saveuser","/roleNames","/gettingAllManagernames").permitAll()// Allow unauthenticated access to these endpoints
                .requestMatchers("/v2/api-docs", "/swagger-resources/configuration/ui", "/swagger-resources/**", "/swagger-resources/configuration/security", "/swagger-ui.html", "/webjars/**").permitAll().requestMatchers("/api/").permitAll()// Allow access to Swagger API documentation
                .requestMatchers("/seats/count").hasRole("Admin") // Allow access to this endpoint only to users with the Admin role
                .anyRequest().authenticated().and().formLogin().and().httpBasic();// Require authentication for all other requests, and allow both form-based and HTTP basic authentication
    	// Adds JWT filter before UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        DefaultSecurityFilterChain defaultSecurityFilterChain=http.build();// Build the default SecurityFilterChain using the HttpSecurity object
        return defaultSecurityFilterChain;// Return the default SecurityFilterChain
    }
    
 // This method returns a CorsConfigurationSource bean for configuring Cross-Origin Resource Sharing (CORS) for the application
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        // Log a message to indicate that CORS is being configured
        logger.info("Configuring CORS");
        // Create a new CorsConfiguration object and set the allowed origins, methods, and headers
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        // Create a new UrlBasedCorsConfigurationSource object and register the CorsConfiguration for all paths
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        // Return the UrlBasedCorsConfigurationSource object as the CorsConfigurationSource bean
        return source;
    }



}
