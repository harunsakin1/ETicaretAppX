package com.haruns.eticaretapp.dto.request;

import jakarta.validation.constraints.Email;

public record ForgotPasswordRequestDto(
		@Email
		String email
) {
}