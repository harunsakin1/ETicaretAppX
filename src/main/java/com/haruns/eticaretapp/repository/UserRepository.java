package com.haruns.eticaretapp.repository;

import com.haruns.eticaretapp.entity.User;
import com.haruns.eticaretapp.entity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
	@Query("SELECT u FROM User u WHERE u.email =?1 AND u.password =?2")
	Optional<User> findByPasswordAndEmail(String email, String password);
	
	@Query("SELECT u.storeName FROM User u WHERE u.id IN (?1)")
	List<String> findAllStoreNameByIds(List<String> userIds);
	
    Optional<User> findById(String id);
	
	@Query("SELECT u.id FROM User u WHERE u.role =?1")
    List<String> findAllUserIdsByRole(Role role);
}