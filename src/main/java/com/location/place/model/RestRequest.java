package com.location.place.model;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class RestRequest<R> {
    private String url;
    private Object[] uriVariables;
    private HttpMethod method;
    private HttpEntity<?> entity;
    private Class<R> resultType;
    private LocalDateTime requestTime;

    public String getUrl() {
        return url;
    }

    public Object[] getUriVariables() {
        return uriVariables;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public HttpEntity<?> getEntity() {
        return entity;
    }

    public Class<R> getResultType() {
        return resultType;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public static final class Builder<R> {
        private String url;
        private Object[] uriVariables;
        private HttpMethod method;
        private HttpEntity<?> entity;
        private Class<R> resultType;
        private LocalDateTime requestTime;
        private HttpHeaders header;
        private Object body;

        public Builder(Class<R> resultType) {
            this.resultType = resultType;
        }

        public Builder<R> url(String url, Object... uriVariables) {
            this.url = url;
            this.uriVariables = uriVariables;
            return this;
        }

        public Builder<R> url(String domain, String uri, Object... uriVariables) {
            url(domain + uri, uriVariables);
            return this;
        }

        public Builder<R> method(HttpMethod method) {
            this.method = method;
            return this;
        }

        public Builder<R> header(HttpHeaders header) {
            this.header = header;
            return this;
        }

        public Builder<R> header(Consumer<HttpHeaders> consumer) {
            if (header == null) {
                header = new HttpHeaders();
            }
            consumer.accept(header);
            return this;
        }

        public Builder<R> accept(MediaType mediaType) {
            header(h -> {
                List<MediaType> accept = h.getAccept();
                if (accept == null) {
                    h.setAccept(Collections.singletonList(mediaType));
                } else {
                    List<MediaType> list = new ArrayList<>();
                    list.add(mediaType);
                    list.addAll(accept);
                    h.setAccept(list);
                }
            });
            return this;
        }

        public Builder<R> contentType(MediaType mediaType) {
            header(h -> h.setContentType(mediaType));
            return this;
        }

        public Builder<R> body(Object body) {
            this.body = body;
            return this;
        }

        public RestRequest build() {
            RestRequest restRequest = new RestRequest();
            restRequest.method = method;

            if (Objects.isNull(header)) {
                header = new HttpHeaders();
            }

            if (Objects.isNull(header.getContentType())) {
                header.setContentType(MediaType.APPLICATION_JSON);
            }

            if (Objects.isNull(header.getAccept())) {
                accept(MediaType.APPLICATION_JSON);
            }

            restRequest.entity = new HttpEntity<>(body, header);
            restRequest.requestTime = LocalDateTime.now();
            restRequest.uriVariables = this.uriVariables;
            restRequest.url = this.url;
            restRequest.resultType = this.resultType;
            return restRequest;
        }
    }
}
