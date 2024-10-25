package com.haruns.eticaretapp.service;

import com.haruns.eticaretapp.dto.request.*;
import com.haruns.eticaretapp.dto.response.GetMyProfileResponseDto;
import com.haruns.eticaretapp.dto.response.IMyProfile;
import com.haruns.eticaretapp.entity.User;
import com.haruns.eticaretapp.entity.VerificationToken;
import com.haruns.eticaretapp.entity.enums.Role;
import com.haruns.eticaretapp.entity.enums.State;
import com.haruns.eticaretapp.entity.enums.UserStatus;
import com.haruns.eticaretapp.exception.ErrorType;
import com.haruns.eticaretapp.exception.EticaretException;
import com.haruns.eticaretapp.mapper.UserMapper;
import com.haruns.eticaretapp.repository.UserRepository;
import com.haruns.eticaretapp.utility.EncryptionManager;
import com.haruns.eticaretapp.utility.JwtManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final VerificationTokenService verificationTokenService;
	private final JwtManager jwtManager;
	private final MailService mailService;
	
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}
	
	public void register(UserRegisterRequestDto dto)
			throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException,
			       InvalidKeyException {
		User user = UserMapper.INSTANCE.fromRegisterDto(dto);
		user.setRole(Role.USER);
		String encryptedPassword = EncryptionManager.encrypt(dto.password());
		user.setPassword(encryptedPassword);
		userRepository.save(user);
		mailService.sendEmail(user.getEmail(), generateVerificationToken(user.getId()));
	}
	
	
	public void storeRegister(StoreRegisterRequestDto dto) {
		User user = UserMapper.INSTANCE.fromStoreRegisterDto(dto);
		user.setRole(Role.SELLER);
		userRepository.save(user);
		mailService.sendEmail(user.getEmail(), generateVerificationToken(user.getId()));
	}
	
	public String login(LoginRequestDto dto) {
		Optional<User> optUser = userRepository.findOptionalByEmailAndPassword(dto.email(), dto.password());
		if (optUser.isEmpty()) {
			throw new EticaretException(ErrorType.INVALID_USERNAME_OR_PASSWORD);
		}
		if (!optUser.get().getStatus().equals(UserStatus.ACTIVE)) {
			throw new EticaretException(ErrorType.USER_NOT_CONFIRMED);
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
		
		if (optUser.get().getRole().equals(Role.SELLER)) {
			return UserMapper.INSTANCE.fromUser(optUser.get());
		}
		return UserMapper.INSTANCE.fromUserEntity(optUser.get());
		
	}
	
	public void updateMyProfile(String token, UpdateMyProfileRequestDto dto) {
		Optional<Long> optUserId = jwtManager.validateToken(token);
		if (optUserId.isEmpty()) {
			throw new EticaretException(ErrorType.INVALID_TOKEN);
		}
		Optional<User> optUser = userRepository.findById(optUserId.get());
		if (optUser.isEmpty()) {
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
		if (optUser.isEmpty()) {
			throw new EticaretException(ErrorType.USER_NOT_FOUND);
		}
		User user = UserMapper.INSTANCE.fromUpdateMyStoreProfileDto(dto);
		user.setId(optUser.get().getId());
		userRepository.save(user);
	}
	
	public String generateVerificationToken(Long userId) {
		StringBuilder verificationTokenSB = new StringBuilder();
		String token = UUID.randomUUID().toString();
		verificationTokenSB.append(token.substring(0, 16));
		verificationTokenSB.append(System.currentTimeMillis() + (1000 * 60));
		VerificationToken verificationToken =
				VerificationToken.builder().expDate(System.currentTimeMillis() + (1000 * 60))
				                 .token(verificationTokenSB.toString()).userId(userId).build();
		verificationTokenService.save(verificationToken);
		return verificationTokenSB.toString();
	}
	
	public void verifyAccount(String verificationTokenSB) {
		VerificationToken verificationToken = verificationTokenService.findByToken(verificationTokenSB);
		
		if (verificationToken == null || verificationToken.getState()== 0) {
			throw new EticaretException(ErrorType.INVALID_TOKEN);
		}
		Long expDate = Long.parseLong(verificationTokenSB.substring(16));
		if (expDate < System.currentTimeMillis()) {
			throw new EticaretException(ErrorType.EXP_TOKEN_DATE);
		}
		Long userId = verificationToken.getUserId();
		Optional<User> optUser = userRepository.findById(userId);
		if (optUser.isEmpty()) {
			throw new EticaretException(ErrorType.USER_NOT_FOUND);
		}
		optUser.get().setStatus(UserStatus.ACTIVE);
		verificationToken.setState(0);
		verificationTokenService.save(verificationToken);
		userRepository.save(optUser.get());
	}
	
	public List<String> findAllStoreNameByIds(List<Long> ids) {
		return userRepository.findAllStoreNameByIds(ids);
	}
	public Long sellerTokenControl(String token) {
		Optional<Long> optUserId = jwtManager.validateToken(token);
		if (optUserId.isEmpty()) {
			throw new EticaretException(ErrorType.INVALID_TOKEN);
		}
		Optional<User> optUser = findById(optUserId.get());
		if (optUser.isEmpty()) {
			throw new EticaretException(ErrorType.USER_NOT_FOUND);
		}
		if (!optUser.get().getRole().equals(Role.SELLER)) {
			throw new EticaretException(ErrorType.UNAUTHORIZED);
		}
		return optUserId.get();
	}
}