package ru.durnov.doc;

import ru.durnov.chapters.Image;
import ru.durnov.chapters.ImageExtractor;

import java.util.List;

public class DocImageExtractor implements ImageExtractor {
    private final String url;

    public DocImageExtractor(String url) {
        this.url = url;
    }

    @Override
    public List<Image> imageList() {
        return null;
    }
}
