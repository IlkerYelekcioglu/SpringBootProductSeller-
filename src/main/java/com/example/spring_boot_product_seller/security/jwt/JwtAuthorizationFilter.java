package com.example.spring_boot_product_seller.security.jwt;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author ilkeryelekcioglu
 * @date 01.01.2025
 */

public class JwtAuthorizationFilter extends OncePerRequestFilter {

  @Autowired(required = false)
  private JwtProvider jwtProvider;

  public JwtAuthorizationFilter() {
    this.jwtProvider = jwtProvider;
  }


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException
  {
    Authentication authentication = jwtProvider.getAuthentication(request);

    if(authentication != null && jwtProvider.isTokenValid(request)) {
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);
  }
}
