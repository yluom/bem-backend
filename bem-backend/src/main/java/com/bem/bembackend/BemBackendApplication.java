package com.bem.bembackend;

import java.util.Optional; // Added for Optional

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

	/**
	 * Initializes the database with sample Event entities and logs query results at application startup.
	 *
	 * This CommandLineRunner bean saves several Event instances, retrieves and logs all events,
	 * attempts to find an event by ID, and queries events by name for demonstration purposes.
	 */
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
			Optional<Event> customerOptional = repository.findById(1L);
			if (customerOptional.isPresent()) {
				Event customer = customerOptional.get();
				log.info("Customer found with findById(1L):");
				log.info("--------------------------------");
				log.info(customer.toString());
			} else {
				log.info("Customer with findById(1L) not found.");
			}
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
