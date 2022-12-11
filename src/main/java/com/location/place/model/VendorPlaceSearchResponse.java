package com.location.place.model;

import com.location.place.vendor.model.VendrApiType;
import com.location.util.SearchPlaceUtil;

public class VendorPlaceSearchResponse {
    private String removedName;
    private String name;
    private String url;
    private String category;
    private String address;
    private String phone;
    private String x;
    private String y;
    private VendrApiType tag;

    public String getRemovedName() {
        return removedName;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getCategory() {
        return category;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public VendrApiType getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return "VendorPlaceSearchResponse{" +
                "removedName='" + removedName + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", category='" + category + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", x='" + x + '\'' +
                ", y='" + y + '\'' +
                ", tag=" + tag +
                '}';
    }

    public static final class Builder {
        private String name;
        private String url;
        private String category;
        private String address;
        private String phone;
        private String x;
        private String y;
        private VendrApiType tag;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder x(String x) {
            this.x = x;
            return this;
        }

        public Builder y(String y) {
            this.y = y;
            return this;
        }

        public Builder tag(VendrApiType tag) {
            this.tag = tag;
            return this;
        }

        public VendorPlaceSearchResponse build() {
            VendorPlaceSearchResponse vendorPlaceSearchResponse = new VendorPlaceSearchResponse();
            vendorPlaceSearchResponse.name = this.name;
            vendorPlaceSearchResponse.removedName = SearchPlaceUtil.removeSpace(SearchPlaceUtil.removeHtmlTag(this.name));
            vendorPlaceSearchResponse.category = this.category;
            vendorPlaceSearchResponse.address = this.address;
            vendorPlaceSearchResponse.url = this.url;
            vendorPlaceSearchResponse.y = this.y;
            vendorPlaceSearchResponse.tag = this.tag;
            vendorPlaceSearchResponse.x = this.x;
            vendorPlaceSearchResponse.phone = this.phone;
            return vendorPlaceSearchResponse;
        }
    }
}
