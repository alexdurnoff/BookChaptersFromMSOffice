package ru.durnov.doc;

import ru.durnov.chapters.ImageExtractor;
import ru.durnov.chapters.Images;

public class DocImages implements Images {
    private final String url;

    public DocImages(String url) {
        this.url = url;
    }


    @Override
    public ImageExtractor imageExtractor() {
        return new DocImageExtractor(this.url);
    }
}
