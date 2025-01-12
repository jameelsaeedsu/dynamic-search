package com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.repository;

import com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.index.CustomersIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CustomersIndexRepository extends ElasticsearchRepository<CustomersIndex, Long> {
}
