package com.jamme.dev.dyanmic.search.dyanmic.search.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "customer")
@Table(name = "customer")
@Getter
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "customer_number", nullable = false, unique = true)
    private String customerNumber;

    @Column(name = "kycStatus", nullable = false)
    private String kycStatus;

    @Column(name = "is_alive", nullable = false)
    private Boolean isAlive;

    @OneToMany(mappedBy = "customer")
    private List<CustomerProduct> customerProducts;

    public Customer(String name,
                    String customerNumber,
                    String kycStatus,
                    Boolean isAlive) {
        this.name = name;
        this.customerNumber = customerNumber;
        this.kycStatus = kycStatus;
        this.isAlive = isAlive;
    }
}
