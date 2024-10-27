package com.haruns.eticaretapp.dto.request;

import com.haruns.eticaretapp.entity.enums.*;
import jakarta.validation.constraints.NotNull;

public record UpdateProductRequestDto(
	String id,
	String name,
	String description,
	String brand,
	Double price,
	Long stock,
	ProductType productType,
	ComputerRam computerRam,
	ComputerGPU computerGPU,
	ComputerCPU computerCPU,
	ComputerScreenSize computerScreenSize,
	ComputerMotherboard computerMotherboard,
	ClothingMaterial clothingMaterial,
	Size clothingSize,
	Color color,
	Gender gender,
	PhoneCamera phoneCamera,
	PhoneStorage phoneStorage,
	PhoneScreen phoneScreen,
	PhoneCPU phoneCPU
) {
}