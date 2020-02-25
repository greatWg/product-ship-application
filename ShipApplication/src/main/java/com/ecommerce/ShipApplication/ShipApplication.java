package com.ecommerce.ShipApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class ShipApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(ShipApplication.class);

	@Autowired
	private ProductRepository repository;

	public static void main(String[] args) {

		SpringApplication.run(ShipApplication.class, args);
	}

	@Override
	public void run(String... args) {
		// save a few products
		repository.save(new Product("fugiat exercitation adipisicing",43, true, 13));
		repository.save(new Product("mollit cupidatat Lorem",70,true,18));
		repository.save(new Product( "non quis sint",33,false,15));
		repository.save(new Product("voluptate cupidatat non",52,false,18));
		repository.save(new Product("anim amet occaecat",39,true,19));
		repository.save(new Product("cillum deserunt elit",47,false,20));
		repository.save(new Product("adipisicing reprehenderit et",71,false,15));
		repository.save(new Product("ex mollit laboris",80,false,15));

//		// fetch all products
//		log.info("Products found with findAll():");
//		log.info("-------------------------------");
//		for (Product product : repository.findAll()) {
//			log.info(product.toString());
//		}
//		log.info("");
//
//		// fetch an individual products by ID
//		Product productDetails = repository.findByProductId(1L);
//		log.info("Product found with findById(1L):");
//		log.info("--------------------------------");
//		log.info(productDetails.toString());
//		log.info("");
//
//		// fetch products by ShipOnWeekends = false
//		log.info("Product found with findByLastName('Bauer'):");
//		log.info("--------------------------------------------");
//		repository.findByShipOnWeekendsIsFalse();
//
//		log.info("");
	}

}
