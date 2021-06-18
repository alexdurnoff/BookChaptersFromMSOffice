package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import ru.durnov.chapters.ImageExtractor;
import ru.durnov.chapters.Images;

public class DocImages implements Images {
    private final HWPFDocument hwpfDocument;

    public DocImages(HWPFDocument hwpfDocument) {
        this.hwpfDocument = hwpfDocument;
    }


    @Override
    public ImageExtractor imageExtractor() {
        return new DocImageExtractor(this.hwpfDocument);
    }
}
