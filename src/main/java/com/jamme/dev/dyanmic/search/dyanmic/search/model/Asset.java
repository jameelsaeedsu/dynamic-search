package com.jamme.dev.dyanmic.search.dyanmic.search.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "asset")
@Table(name = "asset")
@Getter
@Setter
@NoArgsConstructor
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asset_id", nullable = false)
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "product_detail_id", nullable = false)
    private ProductDetails productDetails;

    public Asset(String type, String name, BigDecimal balance, ProductDetails productDetails) {
        this.type = type;
        this.name = name;
        this.balance = balance;
        this.productDetails = productDetails;
    }
}
