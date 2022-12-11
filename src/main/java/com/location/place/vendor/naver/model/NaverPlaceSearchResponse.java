package com.location.place.vendor.naver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.location.place.model.VendorPlaceSearchRequest;
import com.location.place.model.VendorPlaceSearchResponse;
import com.location.place.vendor.model.PlaceSearchResultConvert;
import com.location.place.vendor.model.VendrApiType;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NaverPlaceSearchResponse implements PlaceSearchResultConvert {
    @JsonProperty("items")
    private List<Item> items;

    @Override
    public String toString() {
        return "NaverPlaceSearchResponse{" +
                "items=" + items +
                '}';
    }

    @Override
    public List<VendorPlaceSearchResponse> convert(VendorPlaceSearchRequest searchRequest) {
        return Optional.ofNullable(items)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(k -> new VendorPlaceSearchResponse.Builder()
                        .address(k.address)
                        .category(k.category)
                        .name(k.title)
                        .phone(k.telephone)
                        .url(k.link)
                        .tag(VendrApiType.NAVER)
                        .build())
                .collect(Collectors.toList());
    }

    public static class Item {
        @JsonProperty("title")
        private String title;
        @JsonProperty("link")
        private String link;
        @JsonProperty("category")
        private String category;
        @JsonProperty("description")
        private String description;
        @JsonProperty("telephone")
        private String telephone;
        @JsonProperty("address")
        private String address;
        @JsonProperty("roadAddress")
        private String roadAddress;
        @JsonProperty("mapx")
        private String mapx;
        @JsonProperty("mapy")
        private String mapy;

        @Override
        public String toString() {
            return "Item{" +
                    "title='" + title + '\'' +
                    ", link='" + link + '\'' +
                    ", category='" + category + '\'' +
                    ", description='" + description + '\'' +
                    ", telephone='" + telephone + '\'' +
                    ", address='" + address + '\'' +
                    ", roadAddress='" + roadAddress + '\'' +
                    ", mapx='" + mapx + '\'' +
                    ", mapy='" + mapy + '\'' +
                    '}';
        }
    }
}
