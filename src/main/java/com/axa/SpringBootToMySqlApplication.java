package com.axa;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.axa.excelreader.ExcelReader;
import com.axa.models.User;
import com.axa.repo.UserRepository;

@SpringBootApplication
public class SpringBootToMySqlApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringBootApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootToMySqlApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository repository) {
		return (args) -> {
			

			List<User> users = ExcelReader.readExcel("C:\\Users\\Yannick.Pire\\Desktop\\test.xls");
			for(User user : users){
				repository.save(user);
			}
			

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (User user : repository.findAll()) {
				log.info(user.toString());
			}
			log.info("");
		};
	}
}
