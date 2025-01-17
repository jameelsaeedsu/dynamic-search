package com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Document(indexName = "customers")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomersIndex {

    @Id
    private String customerNumber;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Text)
    private String kycStatus;

    @Field(type = FieldType.Boolean)
    private Boolean isAlive;

    @Field(type = FieldType.Nested)
    private List<ProductIndex> products;

    public CustomersIndex(String customerNumber,
                          String name,
                          String kycStatus,
                          Boolean isAlive,
                          List<ProductIndex> products) {
        this.customerNumber = customerNumber;
        this.name = name;
        this.kycStatus = kycStatus;
        this.isAlive = isAlive;
        this.products = products;
    }
}
