package com.haruns.eticaretapp.view;

import com.haruns.eticaretapp.entity.ProductComment;
import com.haruns.eticaretapp.entity.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VwProduct {
	VwProductDisplay productDisplay;
	String storeName;
	String categoryName;
	List<String> productUrls;
	List<ProductComment> commentList;
	ClothingMaterial clothingMaterial;
	Size size;
	Color color;
	Gender gender;
	ComputerRam ram;
	ComputerCPU computerCPU;
	ComputerGPU computerGPU;
	ComputerMotherboard computerMotherboard;
	ComputerScreenSize computerScreenSize;
	PhoneCamera phoneCamera;
	PhoneStorage phoneStorage;
	PhoneScreen phoneScreen;
	PhoneCPU phoneCpu;
	
}