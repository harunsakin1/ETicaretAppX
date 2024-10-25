package com.haruns.eticaretapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tblverificationtoken")
public class VerificationToken extends BaseEntity  {
	String token;
	Long userId;
	Long expDate;
	
	public VerificationToken(String token, Long userId) {
		this.token = token;
		this.userId = userId;
	}
}