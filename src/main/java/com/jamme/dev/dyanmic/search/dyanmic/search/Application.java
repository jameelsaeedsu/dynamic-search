package com.jamme.dev.dyanmic.search.dyanmic.search;

import com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.index.AssetIndex;
import com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.index.CustomersIndex;
import com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.index.ProductIndex;
import com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.repository.CustomersIndexRepository;
import com.jamme.dev.dyanmic.search.dyanmic.search.model.*;
import com.jamme.dev.dyanmic.search.dyanmic.search.repository.*;
import com.jamme.dev.dyanmic.search.dyanmic.search.service.CustomerMigrationService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
@Slf4j
public class Application {

	private final CustomerRepo customerRepo;
    private final ProductRepo productRepo;
    private final ProductDetailsRepo productDetailsRepo;
    private final CustomerProductRepo customerProductRepo;
    private final AssetRepo assetRepo;
    private final CustomersIndexRepository customersIndexRepository;
    private final CustomerMigrationService customerMigrationService;

    public Application(CustomerRepo customerRepo,
                       ProductRepo productRepo,
                       ProductDetailsRepo productDetailsRepo,
                       CustomerProductRepo customerProductRepo,
                       AssetRepo assetRepo,
                       CustomersIndexRepository customersIndexRepository,
                       CustomerMigrationService customerMigrationService) {
        this.customerRepo = customerRepo;
        this.productRepo = productRepo;
        this.productDetailsRepo = productDetailsRepo;
        this.customerProductRepo = customerProductRepo;
        this.assetRepo = assetRepo;
        this.customersIndexRepository = customersIndexRepository;
        this.customerMigrationService = customerMigrationService;
    }

    public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @PostConstruct
    private void createCustomers() {

        Product iskKonto = new Product("ISK Konto");
        productRepo.save(iskKonto);

        Customer customer1 = new Customer("John Doe", "199702280000", "WHITE", true);
        Customer customer2 = new Customer("Jane Smith", "19980326000", "RED", true);
        Customer customer3 = new Customer("Alice Johnson", "200101020909", "GREEN", true);

        customerRepo.saveAll(List.of(customer1, customer2, customer3));

        ProductDetails iskKontoCustomer1 = new ProductDetails(iskKonto, BigDecimal.valueOf(5500), "Aktivt");
        ProductDetails iskKontoCustomer2 = new ProductDetails(iskKonto, BigDecimal.valueOf(1000), "Avslutat");
        ProductDetails iskKontoCustomer3 = new ProductDetails(iskKonto, BigDecimal.valueOf(1_000_000), "Uppbyggnad");

        productDetailsRepo.saveAll(List.of(iskKontoCustomer1, iskKontoCustomer2, iskKontoCustomer3));

        Asset fund1 = new Asset("Fund", "Global Fund", BigDecimal.valueOf(2000), iskKontoCustomer1);
        Asset stock1 = new Asset("Stock", "Apple", BigDecimal.valueOf(1500), iskKontoCustomer1);

        iskKontoCustomer1.setAssets(List.of(fund1, stock1));

        Asset fund2 = new Asset("Fund", "Tech Fund", BigDecimal.valueOf(300), iskKontoCustomer2);
        Asset stock3 = new Asset("Stock", "Tesla", BigDecimal.valueOf(500), iskKontoCustomer2);
        iskKontoCustomer2.setAssets(List.of(fund2, stock3));

        Asset fund3 = new Asset("Fund", "Emerging Markets Fund", BigDecimal.valueOf(10000), iskKontoCustomer3);
        Asset stock3Customer3 = new Asset("Stock", "Tesla", BigDecimal.valueOf(25700), iskKontoCustomer3);
        iskKontoCustomer3.setAssets(List.of(fund3, stock3Customer3));

        assetRepo.saveAll(List.of(fund1, stock1, fund2, stock3, stock3Customer3));

        CustomerProduct customerProductCustomer1 = new CustomerProduct(iskKonto, customer1,iskKontoCustomer1);
        CustomerProduct customerProductCustomer3 = new CustomerProduct(iskKonto, customer3,iskKontoCustomer3);
        CustomerProduct customerProductCustomer33 = new CustomerProduct(iskKonto, customer2,iskKontoCustomer2);

        customerProductRepo.saveAll(List.of(customerProductCustomer1, customerProductCustomer3, customerProductCustomer33));

        createCustomerIndex(customer1, iskKontoCustomer1, iskKonto);
        createCustomerIndex(customer2, iskKontoCustomer2, iskKonto);
        createCustomerIndex(customer3, iskKontoCustomer3, iskKonto);

    }

    private void createCustomerIndex(Customer customer, ProductDetails productDetails, Product product) {
        ProductIndex productIndex = new ProductIndex();
        productIndex.setProductName(product.getName());
        productIndex.setBalance(productDetails.getBalance());
        productIndex.setStatus(productDetails.getStatus());

        List<AssetIndex> assetIndexes = productDetails.getAssets().stream()
                .map(asset -> {
                    AssetIndex assetIndex = new AssetIndex();
                    assetIndex.setType(asset.getType());
                    assetIndex.setName(asset.getName());
                    assetIndex.setBalance(asset.getBalance());
                    return assetIndex;
                }).toList();

        productIndex.setAssets(assetIndexes);

        CustomersIndex customerIndex = new CustomersIndex();
        customerIndex.setCustomerNumber(customer.getCustomerNumber());
        customerIndex.setName(customer.getName());
        customerIndex.setKycStatus(customer.getKycStatus());
        customerIndex.setIsAlive(customer.getIsAlive());
        customerIndex.setProducts(List.of(productIndex));

        customersIndexRepository.save(customerIndex);
    }


    /*
    public void migrateCustomersFromSqlToElasticsearch() {
        try {
            List<Customer> customers = customerRepo.findCustomerWithProductsAndDetails();

            List<CustomersIndex> customersIndices = customers.stream()
                    .map(customerMigrationService::mapToCustomersIndex)
                    .toList();

            customersIndexRepository.saveAll(customersIndices);
            log.info("Successfully migrated customers to Elasticsearch");
        } catch (Exception e) {
            log.error("Error during customer migration", e);
            throw new RuntimeException(e);
        }

    }
     */

}
