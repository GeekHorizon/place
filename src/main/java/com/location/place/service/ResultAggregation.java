package com.location.place.service;

import com.location.place.model.VendorPlaceSearchResponse;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
public class ResultAggregation {

    public List<VendorPlaceSearchResponse> aggregation(List<VendorPlaceSearchResponse> vendorPlaceSearchRespons) {
        Map<String, Order> orderMap = new HashMap<>();
        AtomicInteger index = new AtomicInteger(1);
        vendorPlaceSearchRespons.stream()
                .forEachOrdered(p -> {

                    orderMap.compute(p.getRemovedName(), (k, v) -> {
                        if (v == null) {
                            return new Order(p, p.getTag().getRank(), 1, index.getAndIncrement());
                        }

                        if (p.getTag() != v.data.getTag()) {
                            v.count++;
                        }
                        return v;
                    });
                });

        List<Order> sorted = new LinkedList<>(orderMap.values());

        sorted.sort((p1, p2) -> {
            if (p1.count > p2.count) {
                return -1;
            } else if (p1.count < p2.count) {
                return 1;
            } else {
                if (p1.priority < p2.priority) {
                    return -1;
                } else if (p1.priority > p2.priority) {
                    return 1;
                } else {
                    if (p1.index < p2.index) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            }
        });

        return sorted.stream().map(o -> o.data).limit(10).collect(Collectors.toList());
    }

    public static class Order {
        public int count;
        public int priority;
        public int index;
        public VendorPlaceSearchResponse data;

        public Order(VendorPlaceSearchResponse data, int priority, int count, int index) {
            this.count = count;
            this.data = data;
            this.priority = priority;
            this.index = index;
        }
    }
}

