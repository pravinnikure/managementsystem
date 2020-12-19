package com.management.managementsystem.Security;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Stream;

@Component
public class AuthenticationFilter extends OncePerRequestFilter
{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            final String uri = request.getRequestURI();
            if (uri.contains("swagger") || uri.contains("v2") || uri.contains("EMployeResource")) {
                logger.info("JWT-AUTH: Skipping token validation for {}");
                chain.doFilter(request, response);
            } else {
                String jwtToken = extractJwtFromRequest(request);
                // UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(jwtTokenUtil.getUsernameFromToken(jwtToken));
                if ("pravin".equals(jwtToken)) {
                    System.out.println("success");
                    chain.doFilter(request, response);
                } else {
                    System.out.println("Invalid authentication token.");
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid authentication token.");
                }
            }
        } catch (Exception ex) {
            System.out.println("JWT-AUTH:  Malformed JWT Token.");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Malformed JWT Token. JWT strings must contain exactly 2 period characters. Found: 0");
        }
    }
    private String extractJwtFromRequest(HttpServletRequest request) {
        return Stream.of(request)
                .map(req -> req.getHeader("Authorization"))
                .filter(token -> StringUtils.hasText(token))
                .findFirst()
                .orElse(null);
    }
}