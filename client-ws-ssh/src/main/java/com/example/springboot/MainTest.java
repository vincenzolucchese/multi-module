package com.example.springboot;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Iterator;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class MainTest {

	public static void main(String args[]) throws Exception {
		
        Resource resource = new ClassPathResource("SanitelCF.cer");
    	InputStream input = resource.getInputStream();    	
		FileInputStream inputStream  =  new FileInputStream (resource.getFile());
		CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
		X509Certificate cert = (X509Certificate)certFactory.generateCertificate(inputStream);
		
		PublicKey pk = cert.getPublicKey();
		
		System.out.println(cert.toString());
	    
//	    Cipher aesCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	    Cipher aesCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

	    // Initialize the cipher for encryption
	    aesCipher.init(Cipher.ENCRYPT_MODE, pk);

	    // Our cleartext
	    byte[] cleartext = "This is just an example".getBytes();
	    System.out.println(new String(cleartext));
	    
	    byte[] pincode = "1426073406".getBytes();
	    byte[] pincodeCry = aesCipher.doFinal(pincode);
	    System.out.println(Arrays.toString(pincodeCry));

	    
	    // Encrypt the cleartext
	    byte[] ciphertext = aesCipher.doFinal(cleartext);

	    // Initialize the same cipher for decryption
	    aesCipher.init(Cipher.DECRYPT_MODE, pk);

	    // Decrypt the ciphertext
	    byte[] cleartext1 = aesCipher.doFinal(ciphertext);
	    
	    System.out.println(new String(cleartext1));
	    
	    String example = "txIO+4Z3DqZvxrol9o6KmkQjlWSD4SOLdeUigRadhoxVNv7rM3RytJlT1jCHdMEg337cD3D1bvYd/uFLeBT+uZzzZy67Rx5UlWD6bvqXlRQjAMxDH/oGcsxmLtBqLPe8i/ZHdiE1xgEPWj+OX6bOdZJOaQgCa9LY8WQaWOWSvbA=";
	    
	    byte[] base64decodedTokenArr = Base64.getDecoder().decode(example);
	    byte[] decryptedPassword = aesCipher.doFinal(base64decodedTokenArr);
	    
	    System.out.println(new String(decryptedPassword));
		
	}
}
