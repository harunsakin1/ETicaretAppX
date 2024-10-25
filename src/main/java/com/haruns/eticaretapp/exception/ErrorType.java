package com.haruns.eticaretapp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
	INTERNAL_SERVER_ERROR(500,"Sunucuda beklenmeyen bir hata meydana geldi. Lütfen tekrar deneyin",HttpStatus.INTERNAL_SERVER_ERROR),
	VALIDATION_ERROR(400,"Parametrelerinizi kontrol edin",HttpStatus.BAD_REQUEST),
	PASSWORD_ERROR(6001,"Girilen şifreler uyuşmamaktadır",HttpStatus.BAD_REQUEST),
	INVALID_USERNAME_OR_PASSWORD(6002,"Kullanıcı adı veya şifre yanlış.",HttpStatus.BAD_REQUEST),
	INVALID_TOKEN(9001,"Geçersiz token bilgisi.",HttpStatus.BAD_REQUEST),
	USER_NOT_FOUND(6003,"Kullanıcı bulunamadı.",HttpStatus.NOT_FOUND),
	USER_NOT_CONFIRMED(6004,"Üyelik onaylanmadı.",HttpStatus.BAD_REQUEST),
	PRODUCT_NOT_FOUND(7003,"Ürün bulunamadı.",HttpStatus.NOT_FOUND),
	CATEGORY_NOT_FOUND(5001,"Kategori bulunamadı.",HttpStatus.NOT_FOUND),
	UNAUTHORIZED(6005,"Yetkiniz bulunmamaktadır.",HttpStatus.UNAUTHORIZED),
	EXP_TOKEN_DATE(8001,"Token'in geçerlilik tarihi geçmiştir.",HttpStatus.BAD_REQUEST),
	MISSING_FIELDS(7004,"Sectiginiz kategori icin gerekli alanlar eksik.", HttpStatus.BAD_REQUEST);
	
	
	
	
	
	int code;
	String message;
	HttpStatus httpStatus;
}