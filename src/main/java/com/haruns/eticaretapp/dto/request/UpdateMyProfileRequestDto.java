package com.haruns.eticaretapp.dto.request;

import com.haruns.eticaretapp.entity.enums.Role;

public record UpdateMyProfileRequestDto(
		String name,
		String surname,
		String email,
		String phone,
		String password
) {
}