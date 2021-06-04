package ru.durnov.docx;

import ru.durnov.chapters.ImageExtractor;
import ru.durnov.chapters.Images;

public class DocxImages implements Images {
    private final String url;

    public DocxImages(String url) {
        this.url = url;
    }


    @Override
    public ImageExtractor imageExtractor() {
        return new DocxImageExtractor(this.url);
    }
}
