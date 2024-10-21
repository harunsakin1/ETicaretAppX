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
	
	public static final String REGISTER="/register";
	public static final String STORE_REGISTER="/store-register";
	public static final String LOGIN="/do-login";
	public static final String GET_MY_PROFILE="/get-my-profile";
	public static final String UPDATE_MY_PROFILE="/update-my-profile";
	public static final String UPDATE_MY_STORE_PROFILE="/update-my-store-profile";
	
	
	public static final String ADD_PRODUCT="/add-product";
	
	
	
}