package com.jamme.dev.dyanmic.search.dyanmic.search.controller;

import com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.index.CustomersIndex;
import com.jamme.dev.dyanmic.search.dyanmic.search.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = CustomerController.API_PATH, produces = "application/json")
public class CustomerController {

    public static final String API_PATH = "/api/v1/customer";

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/search")
    public List<CustomersIndex> searchCustomers(@RequestBody String query) {
        return customerService.searchCustomersByQuery(query);
    }

}
