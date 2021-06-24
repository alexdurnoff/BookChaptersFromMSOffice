package ru.durnov.chapters;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface Chapter {
    @JsonProperty
    String title();
    @JsonProperty
    int level();
    @JsonProperty
    boolean inline();
    @JsonProperty
    String content();

}
