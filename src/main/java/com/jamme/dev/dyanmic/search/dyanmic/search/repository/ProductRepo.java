package com.jamme.dev.dyanmic.search.dyanmic.search.repository;

import com.jamme.dev.dyanmic.search.dyanmic.search.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
