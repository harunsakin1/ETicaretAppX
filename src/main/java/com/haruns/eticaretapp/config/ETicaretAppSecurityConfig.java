package com.haruns.eticaretapp.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Slf4j
public class ETicaretAppSecurityConfig {
	
//	@Bean
//	public JwtTokenFilter getJwtTokenFilter() {
//		return new JwtTokenFilter();
//	}
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests(req->
//				req.requestMatchers(
//				                    "/v1/dev/user/register","/v1/dev/user/do-login","/v1/dev/user/store-register")
//						.permitAll()
//						.requestMatchers("/v1/dev/product/update-product","/v1/dev/product/confirm-product-status"
//						,"/v1/dev/product/get-pending-products","/v1/dev/product/delete-product","/v1/dev/category" +
//								                 "/add-category").hasRole("ADMIN")
//						.requestMatchers("/v1/dev/product/add-product","/v1/dev/product/update-product"
//						,"/v1/dev/product-image/add-image-to-product","/v1/dev/user/update-my-store-profile"
//						).hasRole("SELLER")
//						.requestMatchers("/v1/dev/address/add-address","/v1/dev/product/get-confirmed-products").hasRole("USER")
//						.anyRequest()
//						.permitAll()
//		);
//		http.csrf(AbstractHttpConfigurer::disable);
//		http.addFilterBefore(getJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//		return http.build();
//	}


}