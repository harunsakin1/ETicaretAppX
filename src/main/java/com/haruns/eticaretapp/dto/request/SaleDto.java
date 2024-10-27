package com.haruns.eticaretapp.dto.request;

import com.haruns.eticaretapp.entity.enums.PaymentType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SaleDto {
	String token;
	PaymentType paymentType;
	String addressId;
}