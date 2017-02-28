package com.axa;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.axa.excelreader.ExcelReader;
import com.axa.models.User;
import com.axa.repo.UserRepository;

@SpringBootApplication
public class SpringBootToMySqlApplication implements CommandLineRunner {

	private String location;
	
	@Value("${name:unknown}")
	private String name;

	private static final Logger log = LoggerFactory.getLogger(SpringBootApplication.class);

	public static void main(String[] args) throws Exception{
		SpringApplication.run(SpringBootToMySqlApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository repository) {
		return (args) -> {

			List<User> users = ExcelReader.readExcel(getLocation());
			for (User user : users) {
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

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		if (args.length > 0) {
			setLocation(args[0]);
		} else {
			setLocation(name);
		}
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
