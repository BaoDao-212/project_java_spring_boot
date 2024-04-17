package com.amigoscode.config.auth;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class JwtAuthenticationFilter extends OncePerRequestFilter {
     JwtService jwtService;
     UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain)
        throws ServletException, IOException {

    // If the request is for authentication, no need to check JWT, so forward the request to the next filter
    if (request.getServletPath().contains("/api/auth")) {
        filterChain.doFilter(request, response);
        return;
    }

    // Extract the Authorization header from the request
    final String authHeader = request.getHeader("Authorization");

    // If the Authorization header is not present or does not start with "Bearer ", forward the request to the next filter
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        filterChain.doFilter(request, response);
        return;
    }
    // Extract the JWT from the Authorization header
    final String jwt = authHeader.substring(7);
    System.out.println(jwt);
    // Extract the username from the JWT
    final String userName = jwtService.extractUsername(jwt);
    System.out.println(userName);
    // If the username is not null and there is no authentication in the security context
    if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        // Load the user details
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        // System.out.println(userName);
        // System.out.println(userDetails);
        // If the JWT is valid, set the authentication in the security context
        if (jwtService.isTokenValid(jwt, userDetails)) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource());
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }
    // Forward the request to the next filter
    filterChain.doFilter(request, response);
}
}
