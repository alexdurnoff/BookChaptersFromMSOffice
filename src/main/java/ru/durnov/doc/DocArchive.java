package ru.durnov.doc;

import ru.durnov.chapters.Archive;
import ru.durnov.chapters.Chapters;
import ru.durnov.chapters.Images;

public class DocArchive implements Archive {
    private final String url;

    public DocArchive(String url) {
        this.url = url;
    }
    
    @Override
    public String url() {
        return this.url.replace(".doc", ".zip");
    }

    @Override
    public Images images() {
        return new DocImages(this.url());
    }

    @Override
    public Chapters chapters() {
        return new DocChapters(this.url());
    }
}
