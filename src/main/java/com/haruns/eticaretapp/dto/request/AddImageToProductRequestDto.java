package com.haruns.eticaretapp.dto.request;

import jakarta.validation.constraints.NotNull;

public record AddImageToProductRequestDto(
		@NotNull
		String productId,
		@NotNull
		String token
) {
}