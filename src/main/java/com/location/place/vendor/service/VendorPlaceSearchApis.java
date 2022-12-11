package com.location.place.vendor.service;

import com.location.place.model.ApiPlaceSearchResponse;
import com.location.place.model.VendorPlaceSearchRequest;
import com.location.place.vendor.model.VendrApiType;
import com.location.util.BeanHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VendorPlaceSearchApis {

    @Autowired
    private BeanHolder<VendrApiType, VendorPlaceSearchApi> beanHolder;

    public ApiPlaceSearchResponse search(VendrApiType tag, VendorPlaceSearchRequest request) {
        VendorPlaceSearchApi vendorPlaceSearchApi = beanHolder.get(tag);
        return vendorPlaceSearchApi.search(request);
    }

    public Set<VendrApiType> getTags() {
        return beanHolder.keySet();
    }
}
