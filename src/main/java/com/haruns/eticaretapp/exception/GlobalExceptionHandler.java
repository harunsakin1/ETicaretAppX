package com.haruns.eticaretapp.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	/**
	 * Tanımlaması yapılmayan diğer tüm hataları yakalamak için RuntimeException yakalayın.
	 *
	 */
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public ResponseEntity<ErrorMessage> runtimeExceptionHandler(RuntimeException exception){
		System.out.println(exception.getMessage());
		return createResponseEntity(ErrorType.INTERNAL_SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR,null);
	}

	
	@ExceptionHandler(EticaretException.class)
	@ResponseBody
	public ResponseEntity<ErrorMessage> eticaretExceptionHandler(EticaretException exception){
		
		// ResponseEntity.ok().build(); -> 200 OK. Success. Herşey yolunda.
		// ResponseEntity.badRequest().build(); -> 400 BadRequest yani gelen istek hatalı.
		// ResponseEntity.internalServerError(); -> 500 sunucu tarafında bir hata oldu.
		return createResponseEntity(exception.getErrorType(),exception.getErrorType().getHttpStatus(),null);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ResponseEntity<ErrorMessage> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception){
		List<String> fieldError=new ArrayList<>();
		exception.getBindingResult().getFieldErrors()
				.forEach(f->{
					// f.getField() -> Hata fırlatan nesnenin değişken adı.
					// f.getDefaultMessage() -> Hataya ait detay bilgisi.
					fieldError.add("Değişken Adı : "+f.getField()+" - Hata Detayı : "+f.getDefaultMessage());
				});
		return createResponseEntity(ErrorType.VALIDATION_ERROR,HttpStatus.BAD_REQUEST,fieldError);
	}
	
	public ResponseEntity<ErrorMessage> createResponseEntity(ErrorType errorType,HttpStatus httpStatus,
	                                                         List<String> fields){
		log.error("TÜM HATALARIN GEÇTİĞİ NOKTA...: "+errorType.getMessage()+fields);
		return new ResponseEntity<>(ErrorMessage.builder()
				                            .fields(fields)
				                            .success(false)
				                            .message(errorType.getMessage())
				                            .code(errorType.getCode())
		                                    .build(),httpStatus);
	}
}