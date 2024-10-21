package com.haruns.eticaretapp.dto.response;


public record GetMyProfileResponseDto (
		String name,
		String surname,
		String email,
		String phone,
		String password
) implements IMyProfile {
}