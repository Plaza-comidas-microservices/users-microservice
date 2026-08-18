package com.pragma.plazacomidas.msusers.infrastructure.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pragma.plazacomidas.msusers.infrastructure.out.security.JwtTokenAdapter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    private final JwtTokenAdapter jwtTokenAdapter;

    public JwtAuthenticationFilter(JwtTokenAdapter jwtTokenAdapter) {
        this.jwtTokenAdapter = jwtTokenAdapter;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader(AUTH_HEADER);

        //Primero se revisa que no venga nulo y que tenga el token, si no, sigue derecho.
        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(BEARER_PREFIX.length()); //así me queda el token puro.

        if (jwtTokenAdapter.isTokenValid(token)) {
            String email = jwtTokenAdapter.extractEmail(token);
            String role = jwtTokenAdapter.extractRole(token);
            Long id = jwtTokenAdapter.extractId(token);

            var authorities = List.of(new SimpleGrantedAuthority(role));
            var authentication = new UsernamePasswordAuthenticationToken(email, id, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}