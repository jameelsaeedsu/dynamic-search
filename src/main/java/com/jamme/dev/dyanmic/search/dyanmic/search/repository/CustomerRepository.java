package com.jamme.dev.dyanmic.search.dyanmic.search.repository;

import com.jamme.dev.dyanmic.search.dyanmic.search.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
