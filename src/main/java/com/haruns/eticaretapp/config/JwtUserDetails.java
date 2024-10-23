package com.haruns.eticaretapp.config;

import com.haruns.eticaretapp.entity.User;
import com.haruns.eticaretapp.exception.ErrorType;
import com.haruns.eticaretapp.exception.EticaretException;
import com.haruns.eticaretapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtUserDetails  {
//	private final UserService userService;
//
//
//
//	public UserDetails getUserById(Long userId){
//		Optional<User> optUser = userService.findById(userId);
//		if (optUser.isEmpty()) {
//			throw new EticaretException(ErrorType.USER_NOT_FOUND);
//		}
//		List<GrantedAuthority> authorities = new ArrayList<>();
//		authorities.add(new SimpleGrantedAuthority(optUser.get().getRole().toString()));
//		return org.springframework.security.core.userdetails.User.builder()
//				.username(optUser.get().getEmail())
//				.password(optUser.get().getPassword())
//				.accountExpired(false)
//				.accountLocked(false)
//				.authorities(authorities)
//		        .build();
//	}
}