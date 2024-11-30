package com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "customer")
@Getter
@Setter
public class CustomerIndex {

    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String firstName;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String lastName;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String email;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String gender;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String ipAddress;

    @Field(type = FieldType.Keyword)
    private String kycStatus;

}
