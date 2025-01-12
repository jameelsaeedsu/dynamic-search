package com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.index;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductIndex {

    @Field(type = FieldType.Text)
    private String productName;

    @Field(type = FieldType.Double)
    private BigDecimal balance;

    @Field(type = FieldType.Text)
    private String status;

    @Field(type = FieldType.Nested)
    private List<AssetIndex> assets;

    public ProductIndex(String productName, BigDecimal balance, String status, List<AssetIndex> assets) {
        this.productName = productName;
        this.balance = balance;
        this.status = status;
        this.assets = assets;
    }
}
