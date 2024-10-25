package com.haruns.eticaretapp.utility;

import com.haruns.eticaretapp.entity.*;
import com.haruns.eticaretapp.entity.enums.*;
import com.haruns.eticaretapp.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class CreateFakeData {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductImageRepository productImageRepository;
	private Random rnd=new Random();
	
	public void createFakeUserData(){
		for (int i = 0; i <5 ; i++) {
			userRepository.save(User.builder()
			                        .name("User"+i)
					                    .surname("User surname"+i)
					                    .email("email"+i+"@gmail.com")
					                    .phone("phone"+i)
					                    .role(Role.USER)
					                    .status(UserStatus.ACTIVE)
					                    .password("Password+"+i)
			                        .build());
		}
		for (int i = 5; i <10 ; i++) {
			userRepository.save(User.builder()
					                    .storeName("Store Name"+i)
					                    .taxNo("100000"+i)
			                        .email("email"+i+"@gmail.com")
			                        .phone("phone"+i)
			                        .role(Role.SELLER)
					                .status(UserStatus.ACTIVE)
			                        .password("Password+"+i)
			                        .build());
		}
	}
	public void createFakeCategoryData(){
		for (int i = 0; i <5 ; i++) {
			categoryRepository.save(Category.builder()
					                        .name("Category"+i)
			                                .build());
		}
		
	}
//	public void createFakeProductData(){
//		for (int i = 0; i < 10; i++) {
//			productRepository.save(ComputerProduct.builder()
//					                       .name("Computer"+i)
//			                                       .brand("brand"+i)
//			                                       .status(ProductStatus.ACCEPTED)
//			                                       .description("Description"+i)
//			                                       .categoryId(rnd.nextLong(1,5))
//			                                       .code("code"+i)
//			                                       .totalRating(rnd.nextDouble(1,5))
//					                       .cpu(ComputerCPU.i3)
//					                       .gpu(ComputerGPU.GTX1XXX)
//					                       .screenSize(ComputerScreenSize.INCH_15)
//			                                      .build());
//
//		}
//		for (int i = 0; i < 10; i++) {
//			productRepository.save(Product.builder()
//			                              .name("Product"+i)
//			                              .brand("brand"+i)
//			                              .status(ProductStatus.ACCEPTED)
//			                              .description("Description"+i)
//			                              .categoryId(rnd.nextLong(1,5))
//			                              .code("code"+i)
//			                              .totalRating(rnd.nextDouble(1,5))
//			                              .build());
//		}
//
//	}
//	public void createFakeProductSellerData(){
//		for (long i = 1; i <= 25; i++) {
//			productSellerRepository.save(ProductSeller.builder()
//					                             .productId(i)
//					                             .price(rnd.nextDouble(1,1000))
//					                             .stock(rnd.nextInt(1,100))
//					                             .userId(rnd.nextLong(5,10))
//			                                          .build());
//		}
//	}
	
	public void createFakeUrlData(){
		for (long i = 1; i <= 25; i++) {
			productImageRepository.save(ProductImage.builder()
			                                        .url("url"+i)
					                                .productId(i)
			                                        .build());
		}
		
	}
	//@PostConstruct
	public void createFakeData(){
		createFakeUserData();
		createFakeCategoryData();
	//	createFakeProductData();
		createFakeUrlData();
	}
	
	
}