package com.jamme.dev.dyanmic.search.dyanmic.search.controller;

import com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.dto.CustomerIndex;
import com.jamme.dev.dyanmic.search.dyanmic.search.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = CustomerController.API_PATH, produces = "application/json")
public class CustomerController {

    public static final String API_PATH = "/api/v1/customer";

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /***
     * Approach 1: Building a Free Text Search
     * Approach 2: Is to build a natural language parsing
     */


    @PostMapping("/search")
    public List<CustomerIndex> searchCustomers(@RequestBody String query) {
        return customerService.searchCustomersByQuery(query);
    }

}