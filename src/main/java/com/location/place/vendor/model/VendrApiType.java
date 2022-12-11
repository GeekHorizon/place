package com.location.place.vendor.model;

public enum VendrApiType {
    KAKAO("KAKAO", 1), NAVER("NAVER", 2);

    private int rank;
    private String name;

    VendrApiType(String name, int rank) {
        this.rank = rank;
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }
}
