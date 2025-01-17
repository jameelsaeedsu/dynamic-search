package com.jamme.dev.dyanmic.search.dyanmic.search.controller;

import com.jamme.dev.dyanmic.search.dyanmic.search.dto.CustomerSearchRequest;
import com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.model.CustomersIndex;
import com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.repository.CustomersIndexRepository;
import com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.service.CustomersIndexService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = CustomerController.API_PATH, produces = "application/json")
public class CustomerController {

    public static final String API_PATH = "/api/v1/customer";

    private final CustomersIndexService customersIndexService;

    public CustomerController(CustomersIndexService customersIndexService) {
        this.customersIndexService = customersIndexService;
    }

    @PostMapping("/search")
    public ResponseEntity<List<CustomersIndex>> search(@RequestBody CustomerSearchRequest customerSearchRequest) throws IOException {
            List<CustomersIndex> customers = customersIndexService.search(customerSearchRequest);
            return ResponseEntity.ok(customers);
    }


}
