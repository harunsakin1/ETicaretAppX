package com.haruns.eticaretapp.dto.request;

import jakarta.validation.constraints.NotNull;

public record AddAddressRequestDto(
		@NotNull
		String title,
		@NotNull
		String city,
		@NotNull
		String district,
		@NotNull
		String neighborhood,
		@NotNull
		String street,
		String postalCode,
		@NotNull
		String apartmentNumber
) {
}