package com.gatepass.filters;


import com.gatepass.models.StaffEntity;
import com.gatepass.service.JwtService;
import com.gatepass.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final StaffService staffService;

    Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        logger.info("JwtAuthFilter: Received request - {}", request);
        if (request == null) {
            logger.error("JwtAuthFilter: Request is NULL!!");
        }

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            logger.info("All the Header: {} -> {}", headerName, request.getHeader(headerName));
        }

        final String requestTokenHeader = request.getHeader("Authorization");
        logger.info("Request Header is: {}",requestTokenHeader);
        if(requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            logger.info("JwtAuthFilter: Token header: {}", requestTokenHeader);
            logger.info("JwtAuthFilter: it's null or do not start with Bearer");
            return;
        }



        logger.info("JwtAuthFilter: Inside filter - Request URI: {}", request.getRequestURI());

        logger.info("JwtAuthFilter: Original request token header is {}", requestTokenHeader);
        String token = requestTokenHeader.split("Bearer ")[1];
        logger.info("JwtAuthFilter: Extracted token is {}", token);

        Long userId = jwtService.getUserId(token);

        if(userId!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            StaffEntity staffEntity = staffService.getUserByID(userId);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(staffEntity, null, null);

            authenticationToken.setDetails(  //will add extra details including user ip address etc
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response); // let the other filter continue their jobs also
    }
}
