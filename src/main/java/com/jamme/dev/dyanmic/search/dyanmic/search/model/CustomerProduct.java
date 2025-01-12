package com.jamme.dev.dyanmic.search.dyanmic.search.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer_product")
@Getter
@NoArgsConstructor
public class CustomerProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_product_id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_detail_id", nullable = false)
    private ProductDetails productDetails;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public CustomerProduct(Product product, Customer customer, ProductDetails productDetails) {
        this.product = product;
        this.customer = customer;
        this.productDetails = productDetails;
    }
}
