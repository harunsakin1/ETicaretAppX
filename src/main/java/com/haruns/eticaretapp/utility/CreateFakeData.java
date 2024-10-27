package com.haruns.eticaretapp.utility;

import com.haruns.eticaretapp.entity.*;
import com.haruns.eticaretapp.entity.enums.*;
import com.haruns.eticaretapp.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;

@Component
public class CreateFakeData {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductImageRepository productImageRepository;
	@Autowired
	private EntityIdOperator entityIdOperator;
	@Autowired
	private ClothingProductRepository clothingProductRepository;
	@Autowired
	private ComputerProductRepository computerProductRepository;
	@Autowired
	private PhoneProductRepository phoneProductRepository;
	
	private Random rnd = new Random();
	
	public void createFakeUserData()
			throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException,
			       InvalidKeyException {
		for (int i = 0; i < 5; i++) {
			userRepository.save(User.builder().id(entityIdOperator.generateUniqueIdForOtherEntities()).name("User" + i)
			                        .surname("User surname" + i).email("email" + i + "@gmail.com").phone("phone" + i)
			                        .role(Role.USER).status(UserStatus.ACTIVE).password(EncryptionManager.encrypt("Alperen1+")).build());
		}
		for (int i = 5; i < 10; i++) {
			userRepository.save(User.builder().id(entityIdOperator.generateUniqueIdForOtherEntities())
			                        .storeName("Store Name" + i).taxNo("100000" + i).email("email" + i + "@gmail.com")
			                        .phone("phone" + i).role(Role.SELLER).status(UserStatus.ACTIVE)
			                        .password(EncryptionManager.encrypt("Alperen1+")).build());
		}
		userRepository.save(User.builder().id(entityIdOperator.generateUniqueIdForOtherEntities()).name("Admin")
		                        .surname("Admin").email("admin@gmail.com").phone("admin").role(Role.ADMIN)
		                        .status(UserStatus.ACTIVE).password(EncryptionManager.encrypt("Alperen1+")).build());
	}
	
	public void createFakeCategoryData() {
		categoryRepository.save(Category.builder().id(entityIdOperator.generateUniqueIdForOtherEntities())
		                                .name("Computer").build());
		categoryRepository.save(Category.builder().id(entityIdOperator.generateUniqueIdForOtherEntities()).name("Phone")
		                                .build());
		categoryRepository.save(Category.builder().id(entityIdOperator.generateUniqueIdForOtherEntities())
		                                .name("Clothing").build());
	}
	
	
	public void createFakeProductData() {
		List<String> sellerIdList = userRepository.findAllUserIdsByRole(Role.SELLER);
		List<String> categoryIds = categoryRepository.findAllIds();
		for (int i = 0; i < 10; i++) {
			computerProductRepository.save(ComputerProduct.builder().type(ProductType.COMPUTER)
			                                              .id(entityIdOperator.generateUniqueIdForProducts(ProductType.COMPUTER))
			                                              .name("Computer" + i)
			                                              .categoryId(categoryIds.get(rnd.nextInt(categoryIds.size())))
			                                              .sellerId(sellerIdList.get(rnd.nextInt(sellerIdList.size())))
			                                              .brand("brand" + i).status(ProductStatus.ACCEPTED)
			                                              .description("Description" + i).code("code" + i)
			                                              .totalRating(rnd.nextDouble(1, 5)).cpu(ComputerCPU.i3)
			                                              .gpu(ComputerGPU.GTX1XXX).price(rnd.nextDouble(1000, 10000))
			                                              .stock(rnd.nextLong(100))
			                                              .screenSize(ComputerScreenSize.INCH_15).build());
			
		}
		List<Gender> genderEnums = List.of(Gender.MALE, Gender.FEMALE, Gender.OTHER);
		List<Color> colorEnums = List.of(Color.RED, Color.BLUE, Color.BLACK, Color.MULTI_COLOR);
		for (int i = 0; i < 10; i++) {
			clothingProductRepository.save(ClothingProduct.builder().type(ProductType.CLOTHING)
			                                              .id(entityIdOperator.generateUniqueIdForProducts(ProductType.CLOTHING))
			                                              .name("Clothing" + i).brand("brand" + i)
			                                              .categoryId(categoryIds.get(rnd.nextInt(categoryIds.size())))
			                                              .sellerId(sellerIdList.get(rnd.nextInt(sellerIdList.size())))
			                                              .status(ProductStatus.ACCEPTED).description("Description" + i)
			                                              .code("code" + i)
			                                              .gender(genderEnums.get(rnd.nextInt(genderEnums.size())))
			                                              .color(colorEnums.get(rnd.nextInt(colorEnums.size())))
			                                              .clothingMaterial(ClothingMaterial.COTTON)
			                                              .price(rnd.nextDouble(1000, 10000)).stock(rnd.nextLong(100))
			                                              .totalRating(rnd.nextDouble(1, 5)).build());
		}
		for (int i = 0; i < 5; i++) {
			phoneProductRepository.save(PhoneProduct.builder().type(ProductType.PHONE)
			                                        .id(entityIdOperator.generateUniqueIdForProducts(ProductType.PHONE))
			                                        .name("Phone" + i).brand("brand" + i).status(ProductStatus.PENDING)
			                                        .description("Description" + i)
			                                        .categoryId(categoryIds.get(rnd.nextInt(categoryIds.size())))
			                                        .sellerId(sellerIdList.get(rnd.nextInt(sellerIdList.size())))
			                                        .code("code" + i).storage(PhoneStorage.GB256)
			                                        .screen(PhoneScreen.OLED_6INCH).camera(PhoneCamera.MP_24)
			                                        .price(rnd.nextDouble(1000, 10000)).stock(rnd.nextLong(100))
			                                        .cpu(PhoneCPU.A17).totalRating(rnd.nextDouble(1, 5)).build());
			
		}
		
	}
	
	public void createFakeUrlData() {
		List<String> productIds = clothingProductRepository.findAllIds();
		for (long i = 1; i <= 50; i++) {
			productImageRepository.save(ProductImage.builder().id(entityIdOperator.generateUniqueIdForOtherEntities())
			                                        .url("url" + i)
			                                        .productId(productIds.get(rnd.nextInt(productIds.size()))).build());
		}
	}
	
//	@PostConstruct
	public void createFakeData()
			throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException,
			       InvalidKeyException {
		createFakeUserData();
		createFakeCategoryData();
		createFakeProductData();
		createFakeUrlData();
	}
}