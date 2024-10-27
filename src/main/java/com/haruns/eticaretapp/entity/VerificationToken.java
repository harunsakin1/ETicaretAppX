package com.haruns.eticaretapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tblverificationtoken")
public class VerificationToken extends BaseEntity  {
	String token;
	String userId;
	Long expDate;
	
	public VerificationToken(String token, String userId) {
		this.token = token;
		this.userId = userId;
	}
}