package com.example.file.operations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.file.operations.filter.FileOperationsFilter;

@SpringBootApplication
public class FileOperationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileOperationsApplication.class, args);
	}
	
	/**
	 * Bean to instantiate FileOperations Filter
	 * 
	 * @return {@link FileOperationsFilter}
	 */
	@Bean
	public FileOperationsFilter getFilter() {
		return new FileOperationsFilter();
	}

}
