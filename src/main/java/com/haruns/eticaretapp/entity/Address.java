package com.haruns.eticaretapp.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbladdress")
public class Address extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Long userId;
	String title;
	String city;
	String district;
	String neighborhood;
	String street;
	String postalCode;
	String apartmentNumber;
}