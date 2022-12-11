package com.location.place.vendor.kakao.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.location.place.model.VendorPlaceSearchRequest;
import com.location.place.model.VendorPlaceSearchResponse;
import com.location.place.vendor.model.VendrApiType;
import com.location.place.vendor.model.PlaceSearchResultConvert;

import java.util.*;
import java.util.stream.Collectors;

public class KakaoPlaceSearchResponse implements PlaceSearchResultConvert {
    @JsonProperty("meta")
    private Meta meta;

    @JsonProperty("documents")
    private List<Document> documents;

    @Override
    public List<VendorPlaceSearchResponse> convert(VendorPlaceSearchRequest searchRequest) {
        return Optional.ofNullable(documents)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(k -> new VendorPlaceSearchResponse.Builder()
                        .address(k.roadAddressName)
                        .category(k.categoryName)
                        .name(k.placeName)
                        .phone(k.phone)
                        .url(k.placeUrl)
                        .tag(VendrApiType.KAKAO)
                        .build())
                .collect(Collectors.toList());
    }

    public static class Meta {
        @JsonProperty("same_name")
        private SameName sameName;

        @JsonProperty("pageable_count")
        private Integer pageableCount;

        @JsonProperty("total_count")
        private Integer totalCount;

        @JsonProperty("is_end")
        private Boolean end;

        public static class SameName {
            @JsonProperty("region")
            public List<String> region;

            @JsonProperty("keyword")
            public String keyword;

            @JsonProperty("selected_region")
            public String selectedRegion;
        }
    }

    public static class Document {
        @JsonProperty("id")
        private String id;
        @JsonProperty("place_name")
        private String placeName;
        @JsonProperty("distance")
        private String distance;
        @JsonProperty("place_url")
        private String placeUrl;
        @JsonProperty("phone")
        private String phone;
        @JsonProperty("category_name")
        private String categoryName;
        @JsonProperty("address_name")
        private String addressName;
        @JsonProperty("road_address_name")
        private String roadAddressName;
        @JsonProperty("category_group_code")
        private String categoryGroupCode;
        @JsonProperty("x")
        private String x;
        @JsonProperty("y")
        private String y;
    }
}
