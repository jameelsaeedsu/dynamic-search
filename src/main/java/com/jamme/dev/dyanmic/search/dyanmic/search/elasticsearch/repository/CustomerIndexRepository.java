package com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.repository;

import com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.dto.CustomerIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CustomerIndexRepository extends ElasticsearchRepository<CustomerIndex, Long> {
}
