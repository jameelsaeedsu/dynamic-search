package com.jamme.dev.dyanmic.search.dyanmic.search.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;

@Entity(name = "customer")
@Table(name = "customer")
@Getter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "ip_address", nullable = false)
    private String ipAddress;

    @Column(name = "kycStatus", nullable = false)
    private String kycStatus;

    @Column(name = "amount", nullable = false)
    private String amount;
}
