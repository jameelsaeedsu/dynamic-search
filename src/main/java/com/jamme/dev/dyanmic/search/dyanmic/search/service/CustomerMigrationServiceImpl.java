package com.jamme.dev.dyanmic.search.dyanmic.search.service;

import com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.dto.CustomerIndex;
import com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.repository.CustomerIndexRepository;
import com.jamme.dev.dyanmic.search.dyanmic.search.model.Customer;
import com.jamme.dev.dyanmic.search.dyanmic.search.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerMigrationServiceImpl implements CustomerMigrationService{

    private final CustomerRepository customerRepository;
    private final CustomerIndexRepository elasticCustomerRepository;

    public CustomerMigrationServiceImpl(CustomerRepository customerRepository,
                                        CustomerIndexRepository elasticCustomerRepository) {
        this.customerRepository = customerRepository;
        this.elasticCustomerRepository = elasticCustomerRepository;
    }

    @Override
    @Transactional
    public void migrateCustomersToElasticsearch() {

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

    }


}
