package com.haruns.eticaretapp.repository;

import com.haruns.eticaretapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
	Optional<User> findOptionalByEmailAndPassword(String email, String password);
	
	@Query("SELECT u.storeName FROM User u WHERE u.id IN (?1)")
	List<String> findAllStoreNameByIds(List<Long> userIds);
}