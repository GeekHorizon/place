package com.location.place.service;

import com.location.place.model.VendorPlaceSearchResponse;
import com.location.place.vendor.model.VendrApiType;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class ResultAggregationTest {

    @Test
    public void test1() {
        ResultAggregation resultAggregation = new ResultAggregation();

        List<VendorPlaceSearchResponse> vendorPlaceSearchRespons = new LinkedList<>();

        vendorPlaceSearchRespons.add(new VendorPlaceSearchResponse.Builder().name("테스트").tag(VendrApiType.KAKAO).build());
        vendorPlaceSearchRespons.add(new VendorPlaceSearchResponse.Builder().name("테스트").tag(VendrApiType.KAKAO).build());
        vendorPlaceSearchRespons.add(new VendorPlaceSearchResponse.Builder().name("테스트").tag(VendrApiType.KAKAO).build());
        vendorPlaceSearchRespons.add(new VendorPlaceSearchResponse.Builder().name("테스트").tag(VendrApiType.KAKAO).build());
        vendorPlaceSearchRespons.add(new VendorPlaceSearchResponse.Builder().name("테스트1").tag(VendrApiType.KAKAO).build());
        vendorPlaceSearchRespons.add(new VendorPlaceSearchResponse.Builder().name("테스트2").tag(VendrApiType.KAKAO).build());
        vendorPlaceSearchRespons.add(new VendorPlaceSearchResponse.Builder().name("테스트3").tag(VendrApiType.KAKAO).build());
        vendorPlaceSearchRespons.add(new VendorPlaceSearchResponse.Builder().name("테스트4").tag(VendrApiType.KAKAO).build());

        vendorPlaceSearchRespons.add(new VendorPlaceSearchResponse.Builder().name("테스트5").tag(VendrApiType.NAVER).build());

        List<VendorPlaceSearchResponse> aggregation = resultAggregation.aggregation(vendorPlaceSearchRespons);

        assertTrue(StringUtils.equals(aggregation.get(0).getRemovedName(), "테스트"));
        assertTrue(StringUtils.equals(aggregation.get(1).getRemovedName(), "테스트1"));
        assertTrue(StringUtils.equals(aggregation.get(2).getRemovedName(), "테스트2"));
        assertTrue(StringUtils.equals(aggregation.get(3).getRemovedName(), "테스트3"));
        assertTrue(StringUtils.equals(aggregation.get(4).getRemovedName(), "테스트4"));
        assertTrue(StringUtils.equals(aggregation.get(5).getRemovedName(), "테스트5"));
    }

    @Test
    public void test2() {
        ResultAggregation resultAggregation = new ResultAggregation();

        List<VendorPlaceSearchResponse> vendorPlaceSearchRespons = new LinkedList<>();

        vendorPlaceSearchRespons.add(new VendorPlaceSearchResponse.Builder().name("테스트").tag(VendrApiType.KAKAO).build());
        vendorPlaceSearchRespons.add(new VendorPlaceSearchResponse.Builder().name("테스트").tag(VendrApiType.KAKAO).build());
        vendorPlaceSearchRespons.add(new VendorPlaceSearchResponse.Builder().name("테스트").tag(VendrApiType.KAKAO).build());
        vendorPlaceSearchRespons.add(new VendorPlaceSearchResponse.Builder().name("테스트").tag(VendrApiType.KAKAO).build());
        vendorPlaceSearchRespons.add(new VendorPlaceSearchResponse.Builder().name("테스트1").tag(VendrApiType.KAKAO).build());
        vendorPlaceSearchRespons.add(new VendorPlaceSearchResponse.Builder().name("테스트2").tag(VendrApiType.KAKAO).build());
        vendorPlaceSearchRespons.add(new VendorPlaceSearchResponse.Builder().name("테스트3").tag(VendrApiType.KAKAO).build());
        vendorPlaceSearchRespons.add(new VendorPlaceSearchResponse.Builder().name("테스트4").tag(VendrApiType.KAKAO).build());

        vendorPlaceSearchRespons.add(new VendorPlaceSearchResponse.Builder().name("테스트2").tag(VendrApiType.NAVER).build());
        vendorPlaceSearchRespons.add(new VendorPlaceSearchResponse.Builder().name("테스트4").tag(VendrApiType.NAVER).build());
        vendorPlaceSearchRespons.add(new VendorPlaceSearchResponse.Builder().name("테스트5").tag(VendrApiType.NAVER).build());

        List<VendorPlaceSearchResponse> aggregation = resultAggregation.aggregation(vendorPlaceSearchRespons);

        assertTrue(StringUtils.equals(aggregation.get(0).getRemovedName(), "테스트2"));
        assertTrue(StringUtils.equals(aggregation.get(1).getRemovedName(), "테스트4"));
        assertTrue(StringUtils.equals(aggregation.get(2).getRemovedName(), "테스트"));
        assertTrue(StringUtils.equals(aggregation.get(3).getRemovedName(), "테스트1"));
        assertTrue(StringUtils.equals(aggregation.get(4).getRemovedName(), "테스트3"));
        assertTrue(StringUtils.equals(aggregation.get(5).getRemovedName(), "테스트5"));
    }

    @Test
    public void test3() {
        ResultAggregation resultAggregation = new ResultAggregation();

        List<VendorPlaceSearchResponse> vendorPlaceSearchRespons = new LinkedList<>();

        vendorPlaceSearchRespons.add(new VendorPlaceSearchResponse.Builder().name("테스트").tag(VendrApiType.KAKAO).build());
        vendorPlaceSearchRespons.add(new VendorPlaceSearchResponse.Builder().name("테스트").tag(VendrApiType.KAKAO).build());
        vendorPlaceSearchRespons.add(new VendorPlaceSearchResponse.Builder().name("테스트").tag(VendrApiType.KAKAO).build());
        vendorPlaceSearchRespons.add(new VendorPlaceSearchResponse.Builder().name("테스트").tag(VendrApiType.KAKAO).build());
        vendorPlaceSearchRespons.add(new VendorPlaceSearchResponse.Builder().name("테스트1").tag(VendrApiType.KAKAO).build());
        vendorPlaceSearchRespons.add(new VendorPlaceSearchResponse.Builder().name("테스트2").tag(VendrApiType.KAKAO).build());
        vendorPlaceSearchRespons.add(new VendorPlaceSearchResponse.Builder().name("테스트3").tag(VendrApiType.KAKAO).build());
        vendorPlaceSearchRespons.add(new VendorPlaceSearchResponse.Builder().name("테스트4").tag(VendrApiType.KAKAO).build());


        vendorPlaceSearchRespons.add(new VendorPlaceSearchResponse.Builder().name("테스트").tag(VendrApiType.NAVER).build());
        vendorPlaceSearchRespons.add(new VendorPlaceSearchResponse.Builder().name("테스트4").tag(VendrApiType.NAVER).build());
        vendorPlaceSearchRespons.add(new VendorPlaceSearchResponse.Builder().name("테스트5").tag(VendrApiType.NAVER).build());

        List<VendorPlaceSearchResponse> aggregation = resultAggregation.aggregation(vendorPlaceSearchRespons);

        assertTrue(StringUtils.equals(aggregation.get(0).getRemovedName(), "테스트"));
        assertTrue(StringUtils.equals(aggregation.get(1).getRemovedName(), "테스트4"));
        assertTrue(StringUtils.equals(aggregation.get(2).getRemovedName(), "테스트1"));
        assertTrue(StringUtils.equals(aggregation.get(3).getRemovedName(), "테스트2"));
        assertTrue(StringUtils.equals(aggregation.get(4).getRemovedName(), "테스트3"));
        assertTrue(StringUtils.equals(aggregation.get(5).getRemovedName(), "테스트5"));
    }
}