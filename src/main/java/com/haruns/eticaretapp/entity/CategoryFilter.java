package com.haruns.eticaretapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tblcategory_filter")
public class CategoryFilter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
}