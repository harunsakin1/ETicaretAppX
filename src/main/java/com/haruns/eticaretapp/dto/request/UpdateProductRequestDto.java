package com.haruns.eticaretapp.dto.request;

import jakarta.validation.constraints.NotNull;

public record UpdateProductRequestDto(
	Long id,
	String name,
	String description,
	String brand,
	Double price,
	Integer stock
) {
}