package com.haruns.eticaretapp.config;

import com.haruns.eticaretapp.utility.JwtManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class JwtTokenFilter  { // extends OncePerRequestFilter
//
//	@Autowired
//	private JwtManager jwtManager;
//
//	@Autowired
//	private JwtUserDetails jwtUserDetails;
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		final String authHeader = request.getHeader("Authorization");
//		if (Objects.nonNull(authHeader)&& authHeader.startsWith("Bearer ")){
//			String token = authHeader.substring(7);
//			Optional<Long> optUserId = jwtManager.validateToken(token);
//			if (optUserId.isPresent()){
//				UserDetails userDetails = jwtUserDetails.getUserById(optUserId.get());
//				UsernamePasswordAuthenticationToken authToken =
//						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//				SecurityContextHolder.getContext().setAuthentication(authToken);
//			}
//		}
//		filterChain.doFilter(request, response);
//	}
}