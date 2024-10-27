package com.haruns.eticaretapp.entity;

import com.haruns.eticaretapp.entity.enums.Role;
import com.haruns.eticaretapp.entity.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.SuperBuilder;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbluser")
public class User extends BaseEntity{
	String name;
	String surname;
	@Email
	@Column(unique = true)
	String email;
	String phone;
	@Enumerated(EnumType.STRING)
	Role role;
	String password;
	String taxNo;
	String storeName;
	@Enumerated(EnumType.STRING)
	@Builder.Default
	UserStatus status=UserStatus.PENDING;
	
}