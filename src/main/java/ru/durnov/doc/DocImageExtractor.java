package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import ru.durnov.chapters.Image;
import ru.durnov.chapters.ImageExtractor;
import ru.durnov.docx.EmfImage;
import ru.durnov.docx.WMFImage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DocImageExtractor implements ImageExtractor {
    private final HWPFDocument hwpfDocument;
    private final List<Image> imageList;

    public DocImageExtractor(HWPFDocument hwpfDocument) {
        this.hwpfDocument = hwpfDocument;
        this.imageList = new ArrayList<>();
        this.hwpfDocument.getPicturesTable().getAllPictures().forEach(picture -> {
            if (picture.getMimeType().equals("image/x-emf")) {
                imageList.add(new EmfImage(picture));
            } else if (picture.getMimeType().equals("image/x-wmf")){
                imageList.add(new WMFImage(picture));
            } else {
                imageList.add(new DocImage(picture));
            }
        });
    }

    @Override
    public List<Image> imageList() throws IOException {
        return this.imageList;
    }
}
