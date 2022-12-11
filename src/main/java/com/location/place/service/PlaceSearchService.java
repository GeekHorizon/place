package com.location.place.service;

import com.location.keyword.aspect.ExecutionKeywordRanking;
import com.location.place.model.ApiPlaceSearchResponse;
import com.location.place.model.VendorPlaceSearchRequest;
import com.location.place.model.VendorPlaceSearchResponse;
import com.location.place.vendor.model.VendrApiType;
import com.location.place.vendor.service.VendorPlaceSearchApis;
import com.location.util.ExecutorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
public class PlaceSearchService {
    ExecutorUtil executorUtil = new ExecutorUtil();

    @Autowired
    VendorPlaceSearchApis vendorPlaceSearchApis;

    @Autowired
    ResultAggregation resultAggregation;

    @ExecutionKeywordRanking(expression = "T(java.lang.String).format('%s', #keyword.keyword)")
    public List<VendorPlaceSearchResponse> search(VendorPlaceSearchRequest keyword) {
        List<Future<ApiPlaceSearchResponse>> futureList = vendorPlaceSearchApis.getTags()
                .stream()
                .map(tag -> executorUtil.call(() -> vendorPlaceSearchApis.search(tag, keyword)))
                .collect(Collectors.toList());

        List<VendorPlaceSearchResponse> results = futureList.stream()
                .map(k -> {
                    try {
                        return k.get().getResponseList();
                    } catch (Exception e) {
                        return Collections.<VendorPlaceSearchResponse>emptyList();
                    }
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());

        return resultAggregation.aggregation(results);
    }
}
