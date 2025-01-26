package com.gatepass.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    private final String memberRequestPath="/pages/member-request";
    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception{

        httpSecurity.authorizeHttpRequests(auth -> auth
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers("/index", "/pages/member-request").permitAll()
                .antMatchers("/staticfrags/footer.html", "/staticfrags/header.html").permitAll()
                .antMatchers( "/mediafiles/JSCOE_logo.png").permitAll()
                .anyRequest().permitAll())
                .csrf().disable()
                .logout(LogoutConfigurer::permitAll);

//        httpSecurity
//                .formLogin(form -> form
//                        .loginPage("/index") // Specify custom login page if any
//                        .defaultSuccessUrl("/index", true) // Redirect on successful login
//                        .permitAll());


        return httpSecurity.build();

    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
        return authConfig.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
           return new BCryptPasswordEncoder();
    }

}