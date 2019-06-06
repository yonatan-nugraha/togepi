package com.example.togepi.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class CorsFilter extends OncePerRequestFilter {

    private static final String HTTP_HEADERS = "accept, accept-language, authorization, content-encoding, content-length, content-type, authorization, x-authorization, random, signature";

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", HTTP_HEADERS);
        response.setHeader("Access-Control-Expose-Headers", HTTP_HEADERS);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "3600");

        filterChain.doFilter(request, response);
    }
}
