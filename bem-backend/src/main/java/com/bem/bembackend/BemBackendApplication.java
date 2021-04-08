package com.bem.bembackend;

import java.util.Arrays;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.bem.bembackend.domain.Event;
import com.bem.bembackend.repository.EventRepository;

@SpringBootApplication
public class BemBackendApplication {

	private static final Logger log = LoggerFactory.getLogger(BemBackendApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BemBackendApplication.class, args);
	}

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin, Accept", "X-Requested-With", "Access-Control-Request-Method",
				"Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

	// @Bean
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

	@Bean
	public CommandLineRunner run(EventRepository eventRepository) throws Exception {
		return (String[] args) -> {
			// Let's create random events on startup (easy way to populate database with content lol)
			Random r = new Random();
			Event evt = new Event("Swissbeatbox #" + r.nextInt(1000));
			evt.setLatitude(r.nextInt(360) + r.nextDouble());
			evt.setLongitude(r.nextInt(360) + r.nextDouble());
//			evt.setStartDateTime(LocalDateTime.now().plusDays(r.nextInt(100)));
			eventRepository.save(evt);
			log.debug("Events :");
			eventRepository.findAll().forEach(user -> System.out.print(user.toString() + "\n"));
		};
	}
}
