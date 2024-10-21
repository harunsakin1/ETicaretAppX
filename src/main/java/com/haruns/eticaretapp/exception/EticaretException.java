package com.haruns.eticaretapp.exception;

import lombok.Getter;

@Getter
public class EticaretException extends RuntimeException{
	private ErrorType errorType;
	public EticaretException(ErrorType errorType) {
		super(errorType.getMessage());
		this.errorType=errorType;
	}
}