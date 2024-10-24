package com.haruns.eticaretapp.service;

import com.haruns.eticaretapp.dto.request.AddProductRequestDto;
import com.haruns.eticaretapp.dto.request.UpdateProductRequestDto;
import com.haruns.eticaretapp.entity.Product;
import com.haruns.eticaretapp.entity.ProductSeller;
import com.haruns.eticaretapp.entity.User;
import com.haruns.eticaretapp.entity.enums.ProductStatus;
import com.haruns.eticaretapp.entity.enums.Role;
import com.haruns.eticaretapp.exception.ErrorType;
import com.haruns.eticaretapp.exception.EticaretException;
import com.haruns.eticaretapp.mapper.ProductMapper;
import com.haruns.eticaretapp.repository.ProductRepository;
import com.haruns.eticaretapp.repository.ProductSellerRepository;
import com.haruns.eticaretapp.repository.UserRepository;
import com.haruns.eticaretapp.utility.JwtManager;
import com.haruns.eticaretapp.utility.ProductCodeGenerator;
import com.haruns.eticaretapp.view.VwProduct;
import com.haruns.eticaretapp.view.VwProductDisplay;
import com.haruns.eticaretapp.view.VwProductSeller;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;
	private final ProductSellerService productSellerService;
	private final UserService userService;
	private final JwtManager jwtManager;
	private final ProductSellerRepository productSellerRepository;
	private final UserRepository userRepository;
	private final CategoryService categoryService;
	private final ProductImageService productImageService;
	private Random random=new Random();
	
	public void addProduct(String token, AddProductRequestDto dto) {
		if (!categoryService.existById(dto.categoryId())){
			throw new EticaretException(ErrorType.CATEGORY_NOT_FOUND);
		}
		Optional<User> optUser = sellerTokenControl(token);
		Product product= Product.builder()
					.name(dto.name())
					.description(dto.description())
					.brand(dto.brand())
					.categoryId(dto.categoryId())
					.status(ProductStatus.PENDING)
					.build();
		productRepository.save(product);
		product.setCode(ProductCodeGenerator.generateProductCode(product));
		
		ProductSeller productSeller = ProductSeller.builder()
				.userId(optUser.get().getId())
				.productId(product.getId())
				.stock(dto.stock())
				.price(dto.price())
		        .build();
		productSellerService.save(productSeller);
		
	}
	
	private Optional<User> sellerTokenControl(String token) {
		Optional<Long> optUserId = jwtManager.validateToken(token);
		if (optUserId.isEmpty()) {
			throw new EticaretException(ErrorType.INVALID_TOKEN);
		}
		Optional<User> optUser = userService.findById(optUserId.get());
		if (optUser.isEmpty()) {
			throw new EticaretException(ErrorType.USER_NOT_FOUND);
		}
		if (!optUser.get().getRole().equals(Role.SELLER)) {
			throw new EticaretException(ErrorType.UNAUTHORIZED);
		}
		return optUser;
	}
	
	public boolean existById(Long id){
		return productRepository.existsById(id);
	}
	
	public List<Product> getPendingProducts(String token) {
		adminTokenControl(token);
		return productRepository.findAllByStatus(ProductStatus.PENDING);
		
	}
	
	private boolean adminTokenControl(String token) {
		Optional<Long> optUserId = jwtManager.validateToken(token);
		if (optUserId.isEmpty()) {
			throw new EticaretException(ErrorType.INVALID_TOKEN);
		}
		Optional<User> optUser = userService.findById(optUserId.get());
		if (optUser.isEmpty()) {
			throw new EticaretException(ErrorType.USER_NOT_FOUND);
		}
		if (!optUser.get().getRole().equals(Role.ADMIN)) {
			throw new EticaretException(ErrorType.UNAUTHORIZED);
		}
		return true;
	}
	
	
	public void confirmProductStatus(String token, Long productId) {
		if (adminTokenControl(token)){
			Optional<Product> optProduct = productRepository.findById(productId);
			if (optProduct.isEmpty()) {
				throw new EticaretException(ErrorType.PRODUCT_NOT_FOUND);
			}
			optProduct.get().setStatus(ProductStatus.ACCEPTED);
			productRepository.save(optProduct.get());
		}
	}
	
	public void updateProduct(String token, UpdateProductRequestDto dto) {
		if (sellerTokenControl(token).isPresent()){
			Optional<Product> optProductId = productRepository.findById(dto.id());
			if (optProductId.isEmpty()){
				throw new EticaretException(ErrorType.PRODUCT_NOT_FOUND);
			}
			Product product = ProductMapper.INSTANCE.fromUpdateProductDto(dto);
			productRepository.save(product);
		}
	}
	
	public void deleteProduct(String token, Long productId) {
		if (adminTokenControl(token)){
			Optional<Product> optProductId = productRepository.findById(productId);
			if (optProductId.isEmpty()){
				throw new EticaretException(ErrorType.PRODUCT_NOT_FOUND);
			}
			productRepository.delete(optProductId.get());
		}
	}
	
//	public List<VwProductDisplay> getAllConfirmedProducts() {
//		Pageable pageable= PageRequest.of(0, 20);
//		List<VwProduct> productList = productRepository.getNeededFields(pageable);
//		List<Long> productIds=new ArrayList<>();
//		productList.forEach(p->{
//			productIds.add(p.getId());
//		});
//		List<VwProductSeller> productSellerList = productSellerService.getNeededFields(pageable, productIds);
//		List<Long>userIds=new ArrayList<>();
//		productSellerList.forEach(u->{
//			userIds.add(u.getUserId());
//		                          });
//		List<String> allStoreName = userService.findAllStoreNameByIds(userIds);
//		List<String> allCategoryName = categoryService.findNameByIdIn(productList.stream().map(VwProduct::getCategoryId)
//		                                                                    .collect(Collectors.toList()));
//		List<String> allUrls =
//				productImageService.findUrlByProductIdIn(productList.stream().map(VwProduct::getId)
//				                                                    .collect(Collectors.toList()));
//		List<VwProductDisplay> allProductFields=new ArrayList<>();
//		for (int i = 0; i < 20; i++) {
//			allProductFields.add(VwProductDisplay.builder()
//					                     .vwProduct(productList.get(i))
//					                     .storeName(allStoreName.get())
//					                     .price(productSellerList.get(i).getPrice())
//					                     .url(allUrls.get(i))
//					                     .categoryName(allCategoryName.get())
//			                                     .build());
//		}
//		return allProductFields;
//	}
}