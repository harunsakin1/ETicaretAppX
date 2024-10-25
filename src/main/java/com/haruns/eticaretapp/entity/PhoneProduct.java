package com.haruns.eticaretapp.entity;

import com.haruns.eticaretapp.entity.enums.PhoneCPU;
import com.haruns.eticaretapp.entity.enums.PhoneCamera;
import com.haruns.eticaretapp.entity.enums.PhoneScreen;
import com.haruns.eticaretapp.entity.enums.PhoneStorage;
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
@Table(name = "tblphoneproduct")
public class PhoneProduct extends Product{
	PhoneCamera camera;
	PhoneStorage storage;
	PhoneScreen screen;
	PhoneCPU cpu;
}