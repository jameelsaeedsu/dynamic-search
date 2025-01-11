package com.jamme.dev.dyanmic.search.dyanmic.search.model;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "product_details")
@Table(name = "product_details")
@NoArgsConstructor
public class ProductDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_detail_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(mappedBy = "productDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Asset> assets = new ArrayList<>();

    public ProductDetails(Product product, BigDecimal balance, String status) {
        this.product = product;
        this.balance = balance;
        this.status = status;
    }

    public void setAssets(List<Asset> assets) {
        this.assets.clear();
        if (assets != null) {
            this.assets.addAll(assets);

            for (Asset asset : assets)
                asset.setProductDetails(this);
        }
    }
}
