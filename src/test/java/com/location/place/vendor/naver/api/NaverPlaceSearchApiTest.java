package com.location.place.vendor.naver.api;

import com.location.place.model.ApiPlaceSearchResponse;
import com.location.place.model.VendorPlaceSearchRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@SpringBootTest
@RunWith(SpringRunner.class)
public class NaverPlaceSearchApiTest {
    @Autowired
    private NaverPlaceSearchApi naverPlaceSearchApi;

    @Test
    public void sinario() {
        ApiPlaceSearchResponse responses = naverPlaceSearchApi.search(new VendorPlaceSearchRequest.Builder().keyword("은행").build());
        assertNotNull(responses);
        assertTrue(responses.isSuccess());
    }
}