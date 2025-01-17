package com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.repository;

import com.jamme.dev.dyanmic.search.dyanmic.search.elasticsearch.model.CustomersIndex;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CustomersIndexRepository extends ElasticsearchRepository<CustomersIndex, Long> {

    @Query("{ \"nested\": { \"path\": \"products\", \"query\": { \"bool\": { \"must\": [ { \"match\": { \"products.productName\": \"?0\" } }, { \"nested\": { \"path\": \"products.assets\", \"query\": { \"bool\": { \"must\": [ { \"match\": { \"products.assets.name\": \"?1\" } }, { \"match\": { \"products.assets.balance\": ?2 } } ] } } } } } ] } } } } }")
    List<CustomersIndex> findByProductNameAndAssets(String productName, String assetName, Double assetBalance);

    List<CustomersIndex> findAllByProducts(String productName);

    List<CustomersIndex> findAllByKycStatus(String kycStatus);

}
