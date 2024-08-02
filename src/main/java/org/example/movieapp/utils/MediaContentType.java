package org.example.movieapp.utils;

public enum MediaContentType {
    MOVIE("movie"),
    SERIES("series"),
    TV_SHOW("tv_show");

    private final String value;

    MediaContentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static MediaContentType fromValue(String value) {
        for (MediaContentType type : MediaContentType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown content type: " + value);
    }
}
