package com.bem.bembackend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bem.bembackend.domain.Event;
import com.bem.bembackend.repository.EventRepository;

@SpringBootApplication
public class BemBackendApplication {

	private static final Logger log = LoggerFactory.getLogger(BemBackendApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BemBackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(EventRepository repository) {
		return (args) -> {
			// save a few customers
			repository.save(new Event("Jack"));
			repository.save(new Event("Chloe"));
			repository.save(new Event("Kim"));
			repository.save(new Event("David"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Event customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Event customer = repository.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByName("David").forEach(bauer -> {
				log.info(bauer.toString());
			});
			// for (Customer bauer : repository.findByLastName("Bauer")) {
			// log.info(bauer.toString());
			// }
			log.info("");
		};
	}
}
