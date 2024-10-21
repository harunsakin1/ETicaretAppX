package com.haruns.eticaretapp.dto.request;

import jakarta.validation.constraints.NotNull;

public record AddProductRequestDto(
		@NotNull
		String name,
		@NotNull
		String description,
		@NotNull
		String brand,
		@NotNull
		Double price,
		@NotNull
		Integer stock
) {
}