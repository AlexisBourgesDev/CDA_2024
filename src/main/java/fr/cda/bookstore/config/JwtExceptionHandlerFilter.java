package fr.cda.bookstore.config;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

public class JwtExceptionHandlerFilter extends OncePerRequestFilter{

    public JwtExceptionHandlerFilter() {
        super();
    }

    public void doFilterInternal(
            HttpServletRequest request ,
            HttpServletResponse response , FilterChain chain
    ) throws ServletException, IOException {
        try {
            chain.doFilter(request, response);
        } catch (JwtException e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Token not valid : UNAUTHORIZED");
        }
    }

}