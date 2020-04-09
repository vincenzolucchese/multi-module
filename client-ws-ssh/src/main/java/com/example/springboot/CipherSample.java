package com.example.springboot;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class CipherSample {
   public static void main(String args[]) throws Exception{
      //Creating a Signature object
      Signature sign = Signature.getInstance("SHA256withRSA");
      
      //Creating KeyPair generator object
      KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
      
      //Initializing the key pair generator
      keyPairGen.initialize(2048);
      
      //Generating the pair of keys
      KeyPair pair = keyPairGen.generateKeyPair();   
      
	  	Resource resource = new ClassPathResource("SanitelCF.cer");
		FileInputStream inputStream  =  new FileInputStream (resource.getFile());
		CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
		X509Certificate cert = (X509Certificate)certFactory.generateCertificate(inputStream);	
		PublicKey pk = cert.getPublicKey();	
		System.out.println(cert.toString());
      
	
      //Creating a Cipher object
      Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        
      //Initializing a Cipher object
      cipher.init(Cipher.ENCRYPT_MODE, pk);
	  
      //Adding data to the cipher
      byte[] input = "1426073406".getBytes();	  
      cipher.update(input);
	  
      //encrypting the data
      byte[] cipherText = cipher.doFinal();	 
      System.out.println(Base64.getEncoder().encodeToString(cipherText));
      
      String pincode = "txIO+4Z3DqZvxrol9o6KmkQjlWSD4SOLdeUigRadhoxVNv7rM3RytJlT1jCHdMEg337cD3D1bvYd/uFLeBT+uZzzZy67Rx5UlWD6bvqXlRQjAMxDH/oGcsxmLtBqLPe8i/ZHdiE1xgEPWj+OX6bOdZJOaQgCa9LY8WQaWOWSvbA=";
      cipher.init(Cipher.DECRYPT_MODE, pk);
//      byte[] deco = Base64.getDecoder().decode(pincode);
//      System.out.println(new String(deco));
//      byte[] deco1 = cipher.doFinal(deco);
//      System.out.println(new String(deco1));
      
   }
}


