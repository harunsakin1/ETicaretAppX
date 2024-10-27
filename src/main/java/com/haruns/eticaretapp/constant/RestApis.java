package com.haruns.eticaretapp.constant;

public class RestApis {
	private static final String VERSION="/v1";
	private static final String API="/api";
	private static final String DEVELOPER="/dev";
	private static final String TEST="/test";
	private static final String PROD="/prod";
	
	private static final String ROOT=VERSION+DEVELOPER;
	
	public static final String USER=ROOT+"/user";
	public static final String PRODUCT=ROOT+"/product";
	public static final String PRODUCT_IMAGE=ROOT+"/product-image";
	public static final String CATEGORY=ROOT+"/category";
	public static final String ADDRESS=ROOT+"/address";
	
	public static final String REGISTER="/register";
	public static final String STORE_REGISTER="/store-register";
	public static final String LOGIN="/do-login";
	public static final String GET_MY_PROFILE="/get-my-profile";
	public static final String UPDATE_MY_PROFILE="/update-my-profile";
	public static final String UPDATE_MY_STORE_PROFILE="/update-my-store-profile";
	public static final String ADD_IMAGE_TO_PRODUCT="/add-image-to-product";
	public static final String ADD_CATEGORY="/add-category";
	public static final String ADD_ADDRESS="/add-address";
	public static final String CONFIRM_PRODUCT_STATUS="/confirm-product-status";
	public static final String UPDATE_PRODUCT="/update-product";
	public static final String DELETE_PRODUCT="/delete-product";
	public static final String GET_PENDING_PRODUCTS="/get-pending-products";
	public static final String GET_CONFIRMED_PRODUCTS="/get-confirmed-products";
	public static final String VERIFY_ACCOUNT="/verify-account";
	public static final String ADD_TO_CART="/add-to-cart";
	public static final String REMOVE_FROM_CART="/remove-from-cart";
	public static final String GET_CART="/get-cart";
	public static final String CLEAR_CART="/clear-cart";
	public static final String FILTER_PRODUCTS="/filter-products";
	
	
	
	public static final String ADD_PRODUCT="/add-product";
	
	
	
}