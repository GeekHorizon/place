package com.location.place.vendor.naver.api;

import com.location.place.model.ApiPlaceSearchResponse;
import com.location.place.model.VendorPlaceSearchRequest;
import com.location.place.vendor.model.VendrApiType;
import com.location.place.vendor.naver.model.NaverPlaceSearchResponse;
import com.location.place.vendor.service.VendorPlaceSearchApi;
import com.location.place.model.RestRequest;
import com.location.place.model.RestResponse;
import com.location.place.service.RestTemplateUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class NaverPlaceSearchApi implements VendorPlaceSearchApi {
    private final RestTemplateUtil restTemplateUtil;

    public NaverPlaceSearchApi(RestTemplateUtil restTemplateUtil) {
        this.restTemplateUtil = restTemplateUtil;
    }

    @Override
    public VendrApiType tag() {
        return VendrApiType.NAVER;
    }

    @Value("${api.naver.client.id}")
    private String clientId;

    @Value("${api.naver.client.secret}")
    private String clientSecret;

    @Value("${api.naver.domain}")
    private String domain;

    @Override
    public ApiPlaceSearchResponse search(VendorPlaceSearchRequest searchRequest) {
        RestRequest<NaverPlaceSearchResponse> request = new RestRequest.Builder<>(NaverPlaceSearchResponse.class)
                .method(HttpMethod.GET)
                .accept(MediaType.APPLICATION_JSON)
                .header(h -> {
                    h.add("X-Naver-Client-Id", clientId);
                    h.add("X-Naver-Client-Secret", clientSecret);
                })
                .url(domain, "/v1/search/local.json?query={query}", searchRequest.getKeyword())
                .build();

        RestResponse<NaverPlaceSearchResponse> response = restTemplateUtil.exchange(request);
        return new ApiPlaceSearchResponse.Builder()
                .success(response.isSuccess())
                .vendrApiType(tag())
                .responseList(Optional.ofNullable(response.getResult()).map(r -> r.convert(searchRequest)).orElseGet(Collections::emptyList))
                .build();
    }
}