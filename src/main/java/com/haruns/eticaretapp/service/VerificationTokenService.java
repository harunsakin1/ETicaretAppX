package com.haruns.eticaretapp.service;

import com.haruns.eticaretapp.entity.VerificationToken;
import com.haruns.eticaretapp.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerificationTokenService {
	private final VerificationTokenRepository verificationTokenRepository;
	
	public void save(VerificationToken verificationToken) {
		verificationTokenRepository.save(verificationToken);
	}

	public VerificationToken findByToken(String token) {
		return verificationTokenRepository.findByToken(token);
	}
}