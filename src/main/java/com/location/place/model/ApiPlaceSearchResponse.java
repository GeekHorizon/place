package com.location.place.model;

import com.location.place.vendor.model.VendrApiType;

import java.util.List;

public class ApiPlaceSearchResponse {
    private boolean success;
    private VendrApiType vendrApiType;
    private List<VendorPlaceSearchResponse> responseList;

    public boolean isSuccess() {
        return success;
    }

    public VendrApiType getVendrApiType() {
        return vendrApiType;
    }

    public List<VendorPlaceSearchResponse> getResponseList() {
        return responseList;
    }

    public static final class Builder {
        private boolean success;
        private VendrApiType vendrApiType;
        private List<VendorPlaceSearchResponse> responseList;

        public Builder success(boolean success) {
            this.success = success;
            return this;
        }

        public Builder vendrApiType(VendrApiType vendrApiType) {
            this.vendrApiType = vendrApiType;
            return this;
        }

        public Builder responseList(List<VendorPlaceSearchResponse> responseList) {
            this.responseList = responseList;
            return this;
        }

        public ApiPlaceSearchResponse build() {
            ApiPlaceSearchResponse apiPlaceSearchResponse = new ApiPlaceSearchResponse();
            apiPlaceSearchResponse.vendrApiType = this.vendrApiType;
            apiPlaceSearchResponse.success = this.success;
            apiPlaceSearchResponse.responseList = this.responseList;
            return apiPlaceSearchResponse;
        }
    }
}
