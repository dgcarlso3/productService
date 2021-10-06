package com.carlson.productservice.webservices;

public class WebMedia {
    private final String URL;
    private final String altText;

    private WebMedia(Builder builder) {
        this.URL = builder.URL;
        this.altText = builder.altText;
    }

    public String getURL() {
        return URL;
    }

    public String getAltText() {
        return altText;
    }

    public static WebMedia.Builder builder() {
        return new WebMedia.Builder();
    }

    public static class Builder {
        private String URL;
        private String altText;

        private Builder() {}

        public Builder URL(String URL) {
            this.URL = URL;
            return this;
        }

        public Builder altText(String altText) {
            this.altText = altText;
            return this;
        }

        public WebMedia build() {
            return new WebMedia(this);
        }

    }
}
