package com.haruns.eticaretapp.entity;

import com.haruns.eticaretapp.entity.enums.ClothingMaterial;
import com.haruns.eticaretapp.entity.enums.Color;
import com.haruns.eticaretapp.entity.enums.Gender;
import com.haruns.eticaretapp.entity.enums.Size;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tblclothingproduct")
public class ClothingProduct extends Product{
	ClothingMaterial clothingMaterial;
	Size size;
	Color color;
	Gender gender;
}