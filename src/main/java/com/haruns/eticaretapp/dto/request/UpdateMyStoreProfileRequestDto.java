package com.haruns.eticaretapp.dto.request;

public record UpdateMyStoreProfileRequestDto(
		String email,
		String phone,
		String password,
		String taxNo,
		String storeName
) {
}