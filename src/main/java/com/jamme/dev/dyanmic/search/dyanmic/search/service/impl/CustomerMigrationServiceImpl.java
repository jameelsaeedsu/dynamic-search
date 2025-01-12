package com.jamme.dev.dyanmic.search.dyanmic.search.service.impl;

import com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.index.AssetIndex;
import com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.index.CustomersIndex;
import com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.index.ProductIndex;
import com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.repository.CustomersIndexRepository;
import com.jamme.dev.dyanmic.search.dyanmic.search.model.Customer;
import com.jamme.dev.dyanmic.search.dyanmic.search.model.ProductDetails;
import com.jamme.dev.dyanmic.search.dyanmic.search.repository.CustomerRepo;
import com.jamme.dev.dyanmic.search.dyanmic.search.service.CustomerMigrationService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerMigrationServiceImpl implements CustomerMigrationService {

    private final CustomerRepo customerRepo;
    private final CustomersIndexRepository customersIndexRepository;

    public CustomerMigrationServiceImpl(CustomerRepo customerRepo,
                                        CustomersIndexRepository customersIndexRepository) {
        this.customerRepo = customerRepo;
        this.customersIndexRepository = customersIndexRepository;
    }

    @Override
    @Transactional
    public CustomersIndex mapToCustomersIndex(Customer customer) {

        List<ProductIndex> products = customer.getCustomerProducts().stream()
                .map(customerProduct -> {
                    ProductDetails productDetails = customerProduct.getProductDetails();
                    return new ProductIndex(
                            customerProduct.getProduct().getName(),
                            productDetails.getBalance(),
                            productDetails.getStatus(),
                            productDetails.getAssets().stream()
                                    .map(asset -> new AssetIndex(asset.getType(), asset.getName(), asset.getBalance()))
                                    .toList()
                    );
                })
                .toList();

        return new CustomersIndex(
                customer.getCustomerNumber(),
                customer.getName(),
                customer.getKycStatus(),
                customer.getIsAlive(),
                products
        );

    }

    /*
        public void indexCustomers(List<Customer> customers) {
        List<CustomerIndex> customerIndexes = customers.stream()
                .map(this::mapToCustomerIndex)
                .toList();

        elasticsearchTemplate.save(customerIndexes);
    }
     */


}
