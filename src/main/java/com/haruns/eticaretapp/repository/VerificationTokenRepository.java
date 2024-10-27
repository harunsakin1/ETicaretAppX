package com.haruns.eticaretapp.repository;

import com.haruns.eticaretapp.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, String> {
	@Query("SELECT v FROM VerificationToken v WHERE v.token = :token AND v.state=1" )
	VerificationToken findByToken(String token);
}