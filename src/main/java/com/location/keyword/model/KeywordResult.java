package com.location.keyword.model;

public class KeywordResult {
    public String name;
    public long count;

    public String getName() {
        return name;
    }

    public long getCount() {
        return count;
    }

    public static final class Builder {
        private String name;
        private long count;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder count(long count) {
            this.count = count;
            return this;
        }

        public KeywordResult build() {
            KeywordResult keywordResult = new KeywordResult();
            keywordResult.count = this.count;
            keywordResult.name = this.name;
            return keywordResult;
        }
    }
}
