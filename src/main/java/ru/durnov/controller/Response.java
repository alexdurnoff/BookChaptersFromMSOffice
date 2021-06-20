package ru.durnov.controller;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Response {
    private String url;

    /*
     * @param url - path to archive-file.
     */
    public Response(String url) {
        this.url = url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
