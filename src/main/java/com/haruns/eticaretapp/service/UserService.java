package com.haruns.eticaretapp.service;

import com.haruns.eticaretapp.dto.request.*;
import com.haruns.eticaretapp.dto.response.GetMyProfileResponseDto;
import com.haruns.eticaretapp.dto.response.IMyProfile;
import com.haruns.eticaretapp.entity.User;
import com.haruns.eticaretapp.entity.enums.Role;
import com.haruns.eticaretapp.exception.ErrorType;
import com.haruns.eticaretapp.exception.EticaretException;
import com.haruns.eticaretapp.mapper.UserMapper;
import com.haruns.eticaretapp.repository.UserRepository;
import com.haruns.eticaretapp.utility.JwtManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final JwtManager jwtManager;
	private final MailService mailService;
	
	public Optional<User> findById(Long id){
		return userRepository.findById(id);
	}
	
	
	public void register(UserRegisterRequestDto dto) {
		User user= UserMapper.INSTANCE.fromRegisterDto(dto);
		user.setRole(Role.USER);
		userRepository.save(user);
		mailService.sendEmail(user.getEmail(),"KAYIT HAKKINDA","Başarıyla kayıt yapıldı.");
	}
	
	
	public void storeRegister(StoreRegisterRequestDto dto) {
		User user=UserMapper.INSTANCE.fromStoreRegisterDto(dto);
		user.setRole(Role.SELLER);
		userRepository.save(user);
		mailService.sendEmail(user.getEmail(),"KAYIT HAKKINDA","Başarıyla kayıt yapıldı.");
	}
	
	public String login(LoginRequestDto dto) {
		Optional<User> optUser =
				userRepository.findOptionalByEmailAndPassword(dto.email(), dto.password());
		if (optUser.isEmpty()) {
			throw new EticaretException(ErrorType.INVALID_USERNAME_OR_PASSWORD);
		}
		return createToken(optUser);
	}
	
	private String createToken(Optional<User> optUser) {
		return jwtManager.createToken(optUser.get().getId());
	}
	
	
	public IMyProfile getMyProfile(String token) {
		Optional<Long> optUserId = jwtManager.validateToken(token);
		if (optUserId.isEmpty()) {
			throw new EticaretException(ErrorType.INVALID_TOKEN);
		}
		Optional<User> optUser = userRepository.findById(optUserId.get());
		if (optUser.isEmpty()) {
			throw new EticaretException(ErrorType.USER_NOT_FOUND);
		}
		
		if (optUser.get().getRole().equals(Role.SELLER)){
			return UserMapper.INSTANCE.fromUser(optUser.get());
		}
		return UserMapper.INSTANCE.fromUserEntity(optUser.get());
		
	}
	
	public void updateMyProfile(String token,UpdateMyProfileRequestDto dto) {
		Optional<Long> optUserId = jwtManager.validateToken(token);
		if (optUserId.isEmpty()) {
			throw new EticaretException(ErrorType.INVALID_TOKEN);
		}
		Optional<User> optUser = userRepository.findById(optUserId.get());
		if (optUser.isEmpty()){
			throw new EticaretException(ErrorType.USER_NOT_FOUND);
		}
		User user = UserMapper.INSTANCE.fromUpdateMyProfileDto(dto);
		user.setId(optUser.get().getId());
		userRepository.save(user);
	}
	
	public void updateMyStoreProfile(String token, UpdateMyStoreProfileRequestDto dto) {
		Optional<Long> optUserId = jwtManager.validateToken(token);
		if (optUserId.isEmpty()) {
			throw new EticaretException(ErrorType.INVALID_TOKEN);
		}
		Optional<User> optUser = userRepository.findById(optUserId.get());
		if (optUser.isEmpty()){
			throw new EticaretException(ErrorType.USER_NOT_FOUND);
		}
		User user=UserMapper.INSTANCE.fromUpdateMyStoreProfileDto(dto);
		user.setId(optUser.get().getId());
		userRepository.save(user);
	}
}