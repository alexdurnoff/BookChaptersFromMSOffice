package ru.durnov.docx;

import ru.durnov.chapters.Image;
import ru.durnov.chapters.ImageExtractor;

import java.util.List;

public class DocxImageExtractor implements ImageExtractor {
    private final String url;

    public DocxImageExtractor(String url) {
        this.url = url;
    }

    @Override
    public List<Image> imageList() {
        return null;
    }
}
