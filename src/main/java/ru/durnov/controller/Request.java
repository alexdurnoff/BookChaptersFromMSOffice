package ru.durnov.controller;

import ru.durnov.chapters.Document;
import ru.durnov.doc.Doc;
import ru.durnov.docx.Docx;

public class Request {
    private final String url;

    public Request(String url) {
        this.url = url;
    }

    public Document document(){
        if (url.endsWith(".docx")) return new Docx(url);
        if (url.endsWith(".doc")) return new Doc(url);
        throw new IllegalArgumentException("The url must be ends with .doc or .docx suffix");
    }
}
