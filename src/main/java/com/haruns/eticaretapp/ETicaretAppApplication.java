package com.haruns.eticaretapp;

import com.haruns.eticaretapp.utility.EncryptionManager;
import lombok.Builder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.w3c.dom.Text;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@SpringBootApplication
public class ETicaretAppApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ETicaretAppApplication.class, args);
	}
}