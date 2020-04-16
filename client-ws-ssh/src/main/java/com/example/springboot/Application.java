package com.example.springboot;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}
			
			//clientHttp();
//			certificatoCER();
			
		};
	}
	
	
	private void certificatoCER() throws Exception {
        Resource resource = new ClassPathResource("SanitelCF.cer");
    	InputStream input = resource.getInputStream();    	
		FileInputStream inputStream  =  new FileInputStream (resource.getFile());
		CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
		X509Certificate cert = (X509Certificate)certFactory.generateCertificate(inputStream);
		System.out.println(cert.toString());

		KeyGenerator keygen = KeyGenerator.getInstance("AES");
	    SecretKey aesKey = keygen.generateKey();
	    
	    Cipher aesCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
//	    Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

	    // Initialize the cipher for encryption
	    aesCipher.init(Cipher.ENCRYPT_MODE, aesKey);

	    // Our cleartext
	    byte[] cleartext = "This is just an example".getBytes();
	    System.out.println(new String(cleartext));
	    
	    byte[] pincode = "1426073406".getBytes();
	    byte[] pincodeCry = aesCipher.doFinal(pincode);
	    System.out.println(Arrays.toString(pincodeCry));

	    
	    // Encrypt the cleartext
	    byte[] ciphertext = aesCipher.doFinal(cleartext);

	    // Initialize the same cipher for decryption
	    aesCipher.init(Cipher.DECRYPT_MODE, aesKey);

	    // Decrypt the ciphertext
	    byte[] cleartext1 = aesCipher.doFinal(ciphertext);
	    
	    System.out.println(new String(cleartext1));
	    
	    String example = "txIO+4Z3DqZvxrol9o6KmkQjlWSD4SOLdeUigRadhoxVNv7rM3RytJlT1jCHdMEg337cD3D1bvYd/uFLeBT+uZzzZy67Rx5UlWD6bvqXlRQjAMxDH/oGcsxmLtBqLPe8i/ZHdiE1xgEPWj+OX6bOdZJOaQgCa9LY8WQaWOWSvbA=";
	    
	    byte[] base64decodedTokenArr = Base64.getDecoder().decode(example);
	    byte[] decryptedPassword = aesCipher.doFinal(base64decodedTokenArr);
	    
	    System.out.println(new String(decryptedPassword));
	    
	    
	}
	
	

	
    private static final int TIME_OUT = 10000;    
    private void clientHttp() throws Exception {
    	
    	String URL = "https://invioSS730pTest.sanita.finanze.it/Ricevute730ServiceWeb/ricevutePdf";
        final String FILE = "soapmessage.xml";   
        
        Resource resource = new ClassPathResource(FILE);
    	InputStream input = resource.getInputStream();
    	File file = resource.getFile();
        
    	HttpPost request = new HttpPost(URL);
    	String authHeader = "Basic VVhONlZNREQ6UDJTUERWRjQ=";
    	request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
    	 
	    TrustStrategy acceptingTrustStrategy = (cert, authType) -> true;
	    SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
	    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, 
	      NoopHostnameVerifier.INSTANCE);
	     
	    Registry<ConnectionSocketFactory> socketFactoryRegistry = 
	      RegistryBuilder.<ConnectionSocketFactory> create()
	      .register("https", sslsf)
	      .register("http", new PlainConnectionSocketFactory())
	      .build();
	 
	    BasicHttpClientConnectionManager connectionManager = 
	      new BasicHttpClientConnectionManager(socketFactoryRegistry);
	   
	    HttpHost targetHost = new HttpHost("invioSS730pTest.sanita.finanze.it");
	    

    	
	    AuthCache authCache = new BasicAuthCache();
	    authCache.put(targetHost, new BasicScheme());
	     
	    // Add AuthCache to the execution context
	    HttpClientContext context = HttpClientContext.create();
	    CredentialsProvider credsProvider = new BasicCredentialsProvider();
	    credsProvider.setCredentials(AuthScope.ANY, 
	      new UsernamePasswordCredentials("UXN6VMDD", "P2SPDVF4"));
	    context.setCredentialsProvider(credsProvider);
	    context.setAuthCache(authCache);

	    CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf)
	  	      .setConnectionManager(connectionManager).build();
	    EntityBuilder eb = EntityBuilder.create();
	    eb.setFile(file);
	    HttpEntity aaa = eb.build();
	    
	    request.setEntity(aaa);
	    CloseableHttpResponse response = httpClient.execute(request,context);
	    
	   
	    HttpEntity entity = response.getEntity();	   
	    
	    System.out.println( EntityUtils.toString(entity));
    	int statusCode = response.getStatusLine().getStatusCode();
    	System.out.println(statusCode);
    }

}
