package com.location.place.vendor.model;

import com.location.place.model.VendorPlaceSearchRequest;
import com.location.place.model.VendorPlaceSearchResponse;

import java.util.List;

public interface PlaceSearchResultConvert {
    List<VendorPlaceSearchResponse> convert(VendorPlaceSearchRequest searchRequest);
}
