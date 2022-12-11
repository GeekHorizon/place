package com.location.controller;

import com.location.keyword.model.KeywordResult;
import com.location.keyword.service.KeywordService;
import com.location.place.model.PlaceSearchResult;
import com.location.place.model.VendorPlaceSearchRequest;
import com.location.place.service.PlaceSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/place")
public class PlaceController {

    @Autowired
    private PlaceSearchService placeSearchService;

    @Autowired
    private KeywordService keywordService;

    @GetMapping("/search")
    public List<PlaceSearchResult> search(@RequestParam(required = true) String keyword) {
        return placeSearchService.search(new VendorPlaceSearchRequest.Builder()
                .keyword(keyword)
                .build())
                .stream()
                .map(k -> new PlaceSearchResult(k.getRemovedName()))
                .collect(Collectors.toList());
    }

    @GetMapping("/keyword/ranking")
    public List<KeywordResult> search() {
        return keywordService.topUsed().stream()
                .map(k -> new KeywordResult.Builder()
                        .count(k.getCount())
                        .name(k.getName())
                        .build())
                .collect(Collectors.toList());
    }
}
