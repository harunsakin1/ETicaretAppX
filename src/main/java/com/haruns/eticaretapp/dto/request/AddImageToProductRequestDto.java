package com.haruns.eticaretapp.dto.request;

import jakarta.validation.constraints.NotNull;

public record AddImageToProductRequestDto(
		@NotNull
		Long productId
) {
}