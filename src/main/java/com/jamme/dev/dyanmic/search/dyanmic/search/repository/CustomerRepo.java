package com.jamme.dev.dyanmic.search.dyanmic.search.repository;

import com.jamme.dev.dyanmic.search.dyanmic.search.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CustomerRepo extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c "
            + "JOIN FETCH c.customerProducts cp "
            + "JOIN FETCH cp.product p "
            + "JOIN FETCH p.productDetails pd " +
            "JOIN FETCH pd.assets a")
    List<Customer> findCustomerWithProductsAndDetails();
}
