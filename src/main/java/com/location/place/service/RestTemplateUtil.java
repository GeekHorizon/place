package com.location.place.service;

import com.location.place.model.RestRequest;
import com.location.place.model.RestResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class RestTemplateUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateUtil.class);
    private final RestTemplate restTemplate;

    private RestTemplateUtil() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(5000);  // 읽기시간초과, ms
        factory.setConnectTimeout(3000); // 연결시간초과, ms

        HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(100) // connection pool 적용
                .setMaxConnPerRoute(30) // connection pool 적용
                .build();
        factory.setHttpClient(httpClient);

        this.restTemplate = new RestTemplate(factory);
    }

    public <T> RestResponse<T> exchange(RestRequest<T> request) {
        try {
            ResponseEntity<T> responseEntity = restTemplate.exchange(
                    request.getUrl(),
                    request.getMethod(),
                    request.getEntity(),
                    (Class<T>) request.getResultType(),
                    request.getUriVariables());

            return RestResponse.of(responseEntity.getBody(), responseEntity.getStatusCode());
        } catch (HttpStatusCodeException httpStatusCodeException) {
            return RestResponse.of(httpStatusCodeException, httpStatusCodeException.getStatusCode());
        } catch (Exception e) {
            LOGGER.error(Objects.toString(request), e);
            throw e;
        }
    }
}
