package com.jamme.dev.dyanmic.search.dyanmic.search.service.impl;

import com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.model.CustomersIndex;
import com.jamme.dev.dyanmic.search.dyanmic.search.service.CustomerService;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {


    @Override
    public List<CustomersIndex> searchCustomersByQuery(String query) {
        return List.of();
    }
}
