package com.jamme.dev.dyanmic.search.dyanmic.search;

import com.jamme.dev.dyanmic.search.dyanmic.search.service.CustomerMigrationService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	private final CustomerMigrationService customerMigrationService;

	public Application(CustomerMigrationService customerMigrationService) {
		this.customerMigrationService = customerMigrationService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@PostConstruct
	private void migrateCustomer() {
		customerMigrationService.migrateCustomersToElasticsearch();
	}

}
