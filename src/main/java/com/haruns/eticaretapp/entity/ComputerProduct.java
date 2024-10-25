package com.haruns.eticaretapp.entity;

import com.haruns.eticaretapp.entity.enums.*;
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
@Table(name = "tblcomputerproduct")
public class ComputerProduct extends Product {
	ComputerRam ram;
	ComputerCPU cpu;
	ComputerGPU gpu;
	ComputerMotherboard motherboard;
	ComputerScreenSize screenSize;
}