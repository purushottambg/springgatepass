package com.gatepass.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final String memberRequestPath="/pages/member-request";
    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception{
//        httpSecurity.formLogin(formLoginConfig->formLoginConfig.loginPage("/index.html"));    Here we can define the entry page

//        httpSecurity.formLogin(Customizer.withDefaults());    This is turned to the default behavior

        httpSecurity.authorizeHttpRequests(auth -> auth
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers("/index", "/pages/member-request").permitAll()
                .antMatchers("/staticfrags/footer.html", "/staticfrags/header.html").permitAll()
                .antMatchers( "/staticfrags/mediafiles/JSCOE_logo.png").permitAll()
                .anyRequest().authenticated());

        httpSecurity
                .formLogin(form -> form
                        .loginPage("/index") // Specify custom login page if any
                        .defaultSuccessUrl("/index", true) // Redirect on successful login
                        .permitAll());


        return httpSecurity.build();

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // No password encoding
    }

    //Create your own test user details for testing purpose only
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password("{noop}pass") // {noop} indicates no password encoding
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}