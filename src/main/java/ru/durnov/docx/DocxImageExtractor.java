package ru.durnov.docx;

import ru.durnov.chapters.Image;
import ru.durnov.chapters.ImageExtractor;

import java.util.Collections;
import java.util.List;

public class DocxImageExtractor implements ImageExtractor {
    private final String url;

    public DocxImageExtractor(String url) {
        this.url = url;
    }

    @Override
    public List<Image> imageList() {
        return Collections.singletonList(new Image() {
            @Override
            public String name() {
                return "1.jpeg";
            }

            @Override
            public void saveToArchive(String url) {

            }

            @Override
            public byte[] asByteArray() {
                return this.name().getBytes();
            }
        });
    }//Пока так.
}
