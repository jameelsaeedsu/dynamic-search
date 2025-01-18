package com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.jamme.dev.dyanmic.search.dyanmic.search.dto.CustomerDTO;
import com.jamme.dev.dyanmic.search.dyanmic.search.dto.CustomerSearchRequest;
import com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.model.CustomersIndex;
import com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.service.CustomersIndexService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CustomersIndexServiceImpl implements CustomersIndexService {

    private final ElasticsearchClient elasticsearchClient;

    public CustomersIndexServiceImpl(ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }


    @Override
    public List<CustomerDTO> search(CustomerSearchRequest request) throws IOException {
        SearchRequest searchRequest = buildSearchRequest(request);
        SearchResponse<CustomersIndex> searchResponse = elasticsearchClient.search(searchRequest, CustomersIndex.class);

        List<CustomersIndex> customersIndexList = searchResponse.hits().hits().stream()
                .map(Hit::source)
                .toList();

        return customersIndexList.stream()
                .map(customersIndex -> new CustomerDTO(
                        customersIndex.getCustomerNumber(),
                        customersIndex.getName(),
                        customersIndex.getKycStatus())
                ).toList();
    }

    private SearchRequest buildSearchRequest(CustomerSearchRequest request) {
        BoolQuery.Builder boolQueryBuilder = new BoolQuery.Builder();

        if (request.getProductFilter() != null && request.getProductFilter().getProductName() != null) {
            boolQueryBuilder.must(q -> q.nested(n -> n
                    .path("products")
                    .query(query -> query.match(m -> m
                            .field("products.productName")
                            .query(request.getProductFilter().getProductName())
                    ))
            ));
        }

        if (request.getAssetFilter() != null && request.getAssetFilter().getName() != null) {
            boolQueryBuilder.must(nestedQuery -> nestedQuery
                    .nested(n -> n
                            .path("products.assets")
                            .query(q -> q.match(m -> m.field("products.assets.name").query(request.getAssetFilter().getName())))
                    )
            );
        }

        Query query = new Query.Builder().bool(boolQueryBuilder.build()).build();

        return new SearchRequest.Builder()
                .index("customers")
                .query(query)
                .build();

    }
}
