package com.location.place.vendor.service;

import com.location.place.model.ApiPlaceSearchResponse;
import com.location.place.model.VendorPlaceSearchRequest;
import com.location.place.model.VendorPlaceSearchResponse;
import com.location.place.vendor.model.VendrApiType;

import java.util.List;

public interface VendorPlaceSearchApi {
    VendrApiType tag();

    ApiPlaceSearchResponse search(VendorPlaceSearchRequest searchRequest);
}
