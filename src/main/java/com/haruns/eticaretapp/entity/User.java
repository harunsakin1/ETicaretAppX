package com.haruns.eticaretapp.entity;

import com.haruns.eticaretapp.entity.enums.Role;
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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String name;
	String surname;
	@Email
	String email;
	String phone;
	@Enumerated(EnumType.STRING)
	Role role;
	String password;
	String taxNo;
	String storeName;
	
	
}