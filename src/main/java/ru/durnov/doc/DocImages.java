package ru.durnov.doc;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.poi.hwpf.HWPFDocument;
import ru.durnov.chapters.Image;
import ru.durnov.chapters.ImageExtractor;
import ru.durnov.chapters.Images;

import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DocImages implements Images {
    private final HWPFDocument hwpfDocument;
    private final DocImageExtractor docImageExtractor;

    public DocImages(HWPFDocument hwpfDocument) {
        this.hwpfDocument = hwpfDocument;
        this.docImageExtractor = new DocImageExtractor(this.hwpfDocument);
    }


    @Override
    public ImageExtractor imageExtractor() {
        return this.docImageExtractor;
    }

}
