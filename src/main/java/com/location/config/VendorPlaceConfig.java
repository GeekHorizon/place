package com.location.config;

import com.location.place.vendor.service.VendorPlaceSearchApi;
import com.location.util.BeanHolder;
import com.location.place.vendor.model.VendrApiType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VendorPlaceConfig {
    @Bean
    public BeanHolder<VendrApiType, VendorPlaceSearchApi> vendorPlaceHoder() {
        return new BeanHolder<>(VendorPlaceSearchApi.class, VendorPlaceSearchApi::tag);
    }
}
