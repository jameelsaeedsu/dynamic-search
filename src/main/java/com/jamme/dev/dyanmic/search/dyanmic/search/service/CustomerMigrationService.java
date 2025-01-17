package com.jamme.dev.dyanmic.search.dyanmic.search.service;

import com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.model.CustomersIndex;
import com.jamme.dev.dyanmic.search.dyanmic.search.model.Customer;

public interface CustomerMigrationService {

    CustomersIndex mapToCustomersIndex(Customer customer);
}
