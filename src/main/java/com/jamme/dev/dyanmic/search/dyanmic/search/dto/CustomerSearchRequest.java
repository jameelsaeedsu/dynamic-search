package com.jamme.dev.dyanmic.search.dyanmic.search.dto;

import lombok.Getter;

@Getter
public class CustomerSearchRequest {

    private ProductFilter productFilter;
    private AssetFilter assetFilter;

    @Getter
    public static class ProductFilter {
        private String productName;
    }

    @Getter
    public static class AssetFilter {
        private String name;
    }

}
