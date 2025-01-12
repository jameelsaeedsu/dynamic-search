package com.jamme.dev.dyanmic.search.dyanmic.search.service;

import com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.index.CustomersIndex;

import java.util.List;

public interface CustomerService {
    List<CustomersIndex> searchCustomersByQuery(String query);
}
