package com.haruns.eticaretapp.repository;

import com.haruns.eticaretapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
	Optional<User> findOptionalByEmailAndPassword(String email, String password);
}