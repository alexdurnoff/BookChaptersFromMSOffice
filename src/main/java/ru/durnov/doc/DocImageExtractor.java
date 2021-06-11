package ru.durnov.doc;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import ru.durnov.chapters.Image;
import ru.durnov.chapters.ImageExtractor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DocImageExtractor implements ImageExtractor {
    private final String url;

    public DocImageExtractor(String url) {
        this.url = url;
    }

    @Override
    public List<Image> imageList() throws IOException {

        return null;
    }
}
