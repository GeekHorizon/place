package com.location.place.model;

public class VendorPlaceSearchRequest {
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public static final class Builder {
        private String keyword;

        public Builder keyword(String keyword) {
            this.keyword = keyword;
            return this;
        }

        public VendorPlaceSearchRequest build() {
            VendorPlaceSearchRequest vendorPlaceSearchRequest = new VendorPlaceSearchRequest();
            vendorPlaceSearchRequest.keyword = this.keyword;
            return vendorPlaceSearchRequest;
        }
    }
}
