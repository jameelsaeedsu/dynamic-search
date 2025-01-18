package com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.service;

import com.jamme.dev.dyanmic.search.dyanmic.search.dto.CustomerDTO;
import com.jamme.dev.dyanmic.search.dyanmic.search.dto.CustomerSearchRequest;
import com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.model.CustomersIndex;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CustomersIndexService {

    List<CustomerDTO> search(CustomerSearchRequest customerSearchRequest) throws IOException;

}
