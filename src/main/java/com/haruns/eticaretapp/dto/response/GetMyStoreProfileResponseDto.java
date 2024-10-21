package com.haruns.eticaretapp.dto.response;

public record GetMyStoreProfileResponseDto(
		String email,
		String phone,
		String password,
		String taxNo,
		String storeName
)  implements IMyProfile {
}