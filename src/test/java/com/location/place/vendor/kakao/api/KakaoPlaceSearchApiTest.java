package com.location.place.vendor.kakao.api;

import com.location.place.model.ApiPlaceSearchResponse;
import com.location.place.model.VendorPlaceSearchRequest;
import com.location.place.model.VendorPlaceSearchResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class KakaoPlaceSearchApiTest {
    @Autowired
    private KakaoPlaceSearchApi kakaoPlaceSearchApi;

    @Test
    public void sinario() {
        ApiPlaceSearchResponse response = kakaoPlaceSearchApi.search(new VendorPlaceSearchRequest.Builder().keyword("은행").build());
        assertNotNull(response);
        assertTrue(response.isSuccess());
    }
}