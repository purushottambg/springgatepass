
package com.gatepass.config;

import com.gatepass.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/*
Disabled CSRF: Since JWT handles authentication, CSRF isn’t needed.
Added JwtAuthenticationFilter: This filter intercepts requests and validates the JWT.
Restricted Access: Only /auth/login is public — everything else needs authentication
 */


@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()  // Disable CSRF for stateless authentication
                .authorizeRequests()
                .antMatchers("/index", "/auth/login", "/ops/save-request", "/ops/validate-login").permitAll()  // Public endpoints
                .antMatchers( "/mediafiles/JSCOE_logo.png","/staticfrags/footer.html", "/staticfrags/header.html").permitAll()
                //.anyRequest().permitAll();
                .anyRequest().authenticated();

        // Add the custom JWT filter before Spring's built-in filter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}



/*package com.gatepass.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
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
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception{

        /*
        This is the filter to modify the default security filter chain, we can perform operations like authentication and authorization.
        */

/*
        httpSecurity.authorizeHttpRequests(auth -> auth
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .antMatchers("/index","/pages/member-request","/ops/save-request","/ops/validate-login").permitAll()
                .antMatchers("/pages/member-request").authenticated()
                .antMatchers("/staticfrags/footer.html", "/staticfrags/header.html").permitAll()
                .antMatchers( "/mediafiles/JSCOE_logo.png").permitAll()
                .anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .csrf().disable()
                .logout(LogoutConfigurer::permitAll);

        return httpSecurity.build();

    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
        return authConfig.getAuthenticationManager();
    }
}*/