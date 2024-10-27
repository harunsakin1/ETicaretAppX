package com.haruns.eticaretapp.service;

import com.haruns.eticaretapp.dto.request.AddProductRequestDto;
import com.haruns.eticaretapp.dto.request.ProductFilterDto;
import com.haruns.eticaretapp.dto.request.UpdateProductRequestDto;
import com.haruns.eticaretapp.entity.*;
import com.haruns.eticaretapp.entity.enums.ProductStatus;
import com.haruns.eticaretapp.exception.ErrorType;
import com.haruns.eticaretapp.exception.EticaretException;
import com.haruns.eticaretapp.repository.*;

import com.haruns.eticaretapp.utility.EntityIdOperator;
import com.haruns.eticaretapp.utility.ProductCodeGenerator;
import com.haruns.eticaretapp.utility.ProductSpecification;
import com.haruns.eticaretapp.view.VwProduct;
import com.haruns.eticaretapp.view.VwProductDisplay;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhoneProductService implements MergedService<PhoneProduct>{
	private final PhoneProductRepository phoneProductRepository;
	private final ProductCodeGenerator productCodeGenerator;
	private final EntityIdOperator entityIdOperator;
	private final ProductSpecification<PhoneProduct> productSpecification;
	private final UserRepository userRepository;
	private final CategoryRepository categoryRepository;
	private final ProductImageRepository productImageRepository;
	private final ProductCommentRepository productCommentRepository;
	
	@Override
	public void addProduct(AddProductRequestDto dto, String sellerId) {
		if (dto.getPhoneCamera() == null && dto.getPhoneStorage() == null && dto.getPhoneScreen() == null && dto.getPhoneCPU() == null)
			throw new EticaretException(ErrorType.MISSING_FIELDS);
		PhoneProduct phoneProduct =
				PhoneProduct.builder()
				            .id(entityIdOperator.generateUniqueIdForProducts(dto.getProductType()))
				            .name(dto.getName())
				            .description(dto.getDescription())
				            .brand(dto.getBrand())
				            .price(dto.getPrice())
				            .stock(dto.getStock())
				            .categoryId(dto.getCategoryId())
				            .status(ProductStatus.PENDING)
				            .sellerId(sellerId)
				            .type(dto.getProductType())
				            .cpu(dto.getPhoneCPU())
				            .camera(dto.getPhoneCamera())
				            .screen(dto.getPhoneScreen())
				            .storage(dto.getPhoneStorage())
				            .build();
		phoneProductRepository.save(phoneProduct);
		phoneProduct.setCode(ProductCodeGenerator.generateProductCode(phoneProduct));
		
	}
	@Override
	public List<PhoneProduct> findAllByStatus(ProductStatus status) {
		return phoneProductRepository.findAllByStatus(status);
	}
	@Override
	public Optional<PhoneProduct> findById(String id) {
		return phoneProductRepository.findById(id);
	}
	@Override
	public boolean existById(String id) {
		return phoneProductRepository.existsById(id);
	}
	
	@Override
	public void save(PhoneProduct product) {
		phoneProductRepository.save(product);
	}
	@Override
	public void update(UpdateProductRequestDto dto) {
		PhoneProduct phoneProduct = PhoneProduct.builder()
		                                                 .id(dto.id())
		                                                 .type(dto.productType())
		                                                 .name(dto.name())
		                                                 .description(dto.description())
		                                                 .brand(dto.brand())
		                                                 .price(dto.price())
		                                                 .stock(dto.stock())
				.cpu(dto.phoneCPU())
				.camera(dto.phoneCamera())
				.screen(dto.phoneScreen())
				.storage(dto.phoneStorage())
				.build();
		phoneProductRepository.save(phoneProduct);
	}
	@Override
	public void deleteById(String id) {
        phoneProductRepository.deleteById(id);
    }
	
	@Override
	public List<PhoneProduct> filterProducts(ProductFilterDto filterDto){
		Specification<PhoneProduct> specification = productSpecification.getProductsByFilter(filterDto);
		return phoneProductRepository.findAll(specification);
	}
	
	@Override
	public List<VwProduct> getTop10ByStatus(){
		LinkedList<VwProductDisplay> neededFields = phoneProductRepository.getNeededFields(Pageable.ofSize(10));
		Map<String,String> storeIdNames = new HashMap<>();
		for (VwProductDisplay vwProduct : neededFields) {
            storeIdNames.put(vwProduct.getSellerId(),
                             userRepository.findStoreNameById(vwProduct.getSellerId()));
        }
		Map<String,String> categoryIdNames = new HashMap<>();
		for (VwProductDisplay vwProduct : neededFields) {
            categoryIdNames.put(vwProduct.getCategoryId(),
                    categoryRepository.findNameById(vwProduct.getCategoryId()));
        }
		
		Map<String,List<String>> productIdUrls=new HashMap<>();
		for (VwProductDisplay vwProduct : neededFields) {
            productIdUrls.put(vwProduct.getId(), productImageRepository.findUrlByProductId(vwProduct.getId()));
        }
		Map<String,List<ProductComment>> productIdComments=new HashMap<>();
		for (VwProductDisplay vwProduct : neededFields) {
			productIdComments.put(vwProduct.getId(), productCommentRepository.findCommentsByProductId(vwProduct.getId()));
		}
		List<VwProduct> phoneProductViews = new ArrayList<>();
		for (VwProductDisplay vwProductDisplay : neededFields) {
			VwProduct vwProduct = VwProduct.builder()
					.productDisplay(vwProductDisplay)
					.storeName(storeIdNames.get(vwProductDisplay.getSellerId()))
					.categoryName(categoryIdNames.get(vwProductDisplay.getCategoryId()))
					.productUrls(productIdUrls.get(vwProductDisplay.getId()))
					.commentList(productIdComments.get(vwProductDisplay.getId()))
					.phoneCpu(phoneProductRepository.findCpuById(vwProductDisplay.getId()))
					.phoneCamera(phoneProductRepository.findCameraById(vwProductDisplay.getId()))
					.phoneScreen(phoneProductRepository.findScreenById(vwProductDisplay.getId()))
					.phoneStorage(phoneProductRepository.findStorageById(vwProductDisplay.getId()))
					.build();
			phoneProductViews.add(vwProduct);
		}
		return phoneProductViews;
	}
}