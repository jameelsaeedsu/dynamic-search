package com.jamme.dev.dyanmic.search.dyanmic.search.repository;

import com.jamme.dev.dyanmic.search.dyanmic.search.model.CustomerProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerProductRepo extends JpaRepository<CustomerProduct, Long> {
}
