package com.vince.portal.application;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class MainTest {

	   public static void main(String[] args) {

	        ExecutorService pool = Executors.newSingleThreadExecutor();

	        ProcessBuilder processBuilder = new ProcessBuilder();
	        processBuilder.directory(new File("../form-submission/"));
	    	// -- Linux --
	    	// Run a shell command
	        processBuilder.command("bash", "-c", "java -jar target/form-submission-0.0.1-SNAPSHOT.jar");

	        try {

	            Process process = processBuilder.start();
	            process.waitFor();

	            System.out.println("process ping...");
	            ProcessReadTask task = new ProcessReadTask(process.getInputStream());
	            Future<List<String>> future = pool.submit(task);

	            // no block, can do other tasks here
	            System.out.println("process task1...");
	            System.out.println("process task2...");

	            List<String> result = future.get(5, TimeUnit.SECONDS);
	            for (String s : result) {
	                System.out.println(s);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            pool.shutdown();
	        }
	    }

	    private static class ProcessReadTask implements Callable<List<String>> {

	        private InputStream inputStream;

	        public ProcessReadTask(InputStream inputStream) {
	            this.inputStream = inputStream;
	        }

	        @Override
	        public List<String> call() {
	            return new BufferedReader(new InputStreamReader(inputStream))
					.lines()
					.collect(Collectors.toList());
	        }
	    }

	}
