package com.haruns.eticaretapp.service;

import com.haruns.eticaretapp.dto.request.AddCategoryRequestDto;
import com.haruns.eticaretapp.entity.Category;
import com.haruns.eticaretapp.entity.User;
import com.haruns.eticaretapp.entity.enums.Role;
import com.haruns.eticaretapp.exception.ErrorType;
import com.haruns.eticaretapp.exception.EticaretException;
import com.haruns.eticaretapp.repository.CategoryRepository;
import com.haruns.eticaretapp.utility.JwtManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryService {
	private final CategoryRepository categoryRepository;
	private final UserService userService;
	private final JwtManager jwtManager;
	
	public void addCategory(AddCategoryRequestDto dto) {
		Optional<Long> optUserId = jwtManager.validateToken(dto.token());
		if (optUserId.isEmpty()){
			throw new EticaretException(ErrorType.INVALID_TOKEN);
		}
		Optional<User> optUser = userService.findById(optUserId.get());
		if (optUser.isEmpty()){
			throw new EticaretException(ErrorType.USER_NOT_FOUND);
		}
		if (!optUser.get().getRole().equals(Role.ADMIN)){
			throw new EticaretException(ErrorType.UNAUTHORIZED);
		}
		Category category= Category.builder().name(dto.name()).build();
		categoryRepository.save(category);
	}
}