package com.location.place.vendor.kakao.api;

import com.location.place.model.ApiPlaceSearchResponse;
import com.location.place.model.VendorPlaceSearchRequest;
import com.location.place.vendor.kakao.model.KakaoPlaceSearchResponse;
import com.location.place.vendor.model.VendrApiType;
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
public class KakaoPlaceSearchApi implements VendorPlaceSearchApi {
    private final RestTemplateUtil restTemplateUtil;

    public KakaoPlaceSearchApi(RestTemplateUtil restTemplateUtil) {
        this.restTemplateUtil = restTemplateUtil;
    }

    @Override
    public VendrApiType tag() {
        return VendrApiType.KAKAO;
    }

    @Value("${api.kakao.authorization.key}")
    private String apiKey;

    @Value("${api.kakao.authorization.prefix}")
    private String apiPrefix;

    @Value("${api.kakao.domain}")
    private String domain;


    @Override
    public ApiPlaceSearchResponse search(VendorPlaceSearchRequest searchRequest) {
        RestRequest<KakaoPlaceSearchResponse> request = new RestRequest.Builder<>(KakaoPlaceSearchResponse.class)
                .method(HttpMethod.GET)
                .accept(MediaType.APPLICATION_JSON)
                .header(h -> h.add("Authorization", apiPrefix + apiKey))
                .url(domain, "/v2/local/search/keyword.json?query={query}", searchRequest.getKeyword())
                .build();

        RestResponse<KakaoPlaceSearchResponse> response = restTemplateUtil.exchange(request);

        return new ApiPlaceSearchResponse.Builder()
                .success(response.isSuccess())
                .vendrApiType(tag())
                .responseList(Optional.ofNullable(response.getResult()).map(r -> r.convert(searchRequest)).orElseGet(Collections::emptyList))
                .build();
    }
}
