package com.haruns.eticaretapp.dto.request;

import com.haruns.eticaretapp.entity.enums.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddProductRequestDto {
	@NotNull
	public String name;
	@NotNull
	public String description;
	@NotNull
	public String brand;
	@NotNull
	public Double price;
	@NotNull
	public Long stock;
	@NotNull
	public String categoryId;
	@NotNull
	public ProductType productType;
	public ComputerRam computerRam;
	public ComputerGPU computerGPU;
	public ComputerCPU computerCPU;
	public ComputerScreenSize computerScreenSize;
	public ComputerMotherboard computerMotherboard;
	public ClothingMaterial clothingMaterial;
	public Size clothingSize;
	public Color color;
	public Gender gender;
	public PhoneCamera phoneCamera;
	public PhoneStorage phoneStorage;
	public PhoneScreen phoneScreen;
	public PhoneCPU phoneCPU;
	
}