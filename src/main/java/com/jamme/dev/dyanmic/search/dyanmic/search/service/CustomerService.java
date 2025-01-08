package com.jamme.dev.dyanmic.search.dyanmic.search.service;

import com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.dto.CustomerIndex;

import java.util.List;

public interface CustomerService {
    List<CustomerIndex> searchCustomersByQuery(String query);
}
