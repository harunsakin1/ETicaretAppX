package com.haruns.eticaretapp.service;

import com.haruns.eticaretapp.dto.request.AddAddressRequestDto;
import com.haruns.eticaretapp.entity.Address;
import com.haruns.eticaretapp.entity.User;
import com.haruns.eticaretapp.entity.enums.Role;
import com.haruns.eticaretapp.exception.ErrorType;
import com.haruns.eticaretapp.exception.EticaretException;
import com.haruns.eticaretapp.mapper.AddressMapper;
import com.haruns.eticaretapp.repository.AddressRepository;
import com.haruns.eticaretapp.utility.EntityIdOperator;
import com.haruns.eticaretapp.utility.JwtManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {
	private final AddressRepository addressRepository;
	private final UserService userService;
	private final JwtManager jwtManager;
	private final EntityIdOperator entityIdOperator;
	
	public void addAddress(String token, AddAddressRequestDto dto) {
		Optional<String> optUserId = jwtManager.validateToken(token);
		if (optUserId.isEmpty()) {
			throw new EticaretException(ErrorType.INVALID_TOKEN);
		}
		Optional<User> optUser = userService.findById(optUserId.get());
		if (optUser.isEmpty()){
			throw new EticaretException(ErrorType.USER_NOT_FOUND);
		}
		if (!optUser.get().getRole().equals(Role.USER)) {
			throw new EticaretException(ErrorType.UNAUTHORIZED);
		}
		Address address = AddressMapper.INSTANCE.fromAddAddressDto(dto);
		address.setId(entityIdOperator.generateUniqueIdForOtherEntities());
		address.setUserId(optUser.get().getId());
		addressRepository.save(address);
		
	}
}