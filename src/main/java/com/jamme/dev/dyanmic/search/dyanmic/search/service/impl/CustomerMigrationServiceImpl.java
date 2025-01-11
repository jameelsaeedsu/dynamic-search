package com.jamme.dev.dyanmic.search.dyanmic.search.service.impl;

import com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.repository.CustomerIndexRepository;
import com.jamme.dev.dyanmic.search.dyanmic.search.repository.CustomerRepo;
import com.jamme.dev.dyanmic.search.dyanmic.search.service.CustomerMigrationService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerMigrationServiceImpl implements CustomerMigrationService {

    private final CustomerRepo customerRepo;
    private final CustomerIndexRepository elasticCustomerRepository;

    public CustomerMigrationServiceImpl(CustomerRepo customerRepo,
                                        CustomerIndexRepository elasticCustomerRepository) {
        this.customerRepo = customerRepo;
        this.elasticCustomerRepository = elasticCustomerRepository;
    }

    @Override
    @Transactional
    public void migrateCustomersToElasticsearch() {
/*
        log.info("Customer Migration Initiated");

        List<Customer> customers = customerRepository.findAll();
        List<CustomerIndex> customerIndices = customers.stream()
                .map(customer -> {
                    CustomerIndex index = new CustomerIndex();
                    index.setId(customer.getId());
                    index.setFirstName(customer.getFirstName());
                    index.setLastName(customer.getLastName());
                    index.setEmail(customer.getEmail());
                    index.setGender(customer.getGender());
                    index.setIpAddress(customer.getIpAddress());
                    index.setKycStatus(customer.getKycStatus());
                    return index;
                }).toList();

        elasticCustomerRepository.saveAll(customerIndices);

        log.info("Customer Migration Completed");

 */

    }


}
