package com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.index;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class AssetIndex {

    @Field(type = FieldType.Text)
    private String type;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Double)
    private BigDecimal balance;

    public AssetIndex(String type, String name, BigDecimal balance) {
        this.type = type;
        this.name = name;
        this.balance = balance;
    }
}
