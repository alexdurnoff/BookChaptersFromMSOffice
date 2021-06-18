package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import ru.durnov.chapters.Image;
import ru.durnov.chapters.ImageExtractor;
import ru.durnov.docx.DocxEmfImage;
import ru.durnov.docx.DocxWMFImage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DocImageExtractor implements ImageExtractor {
    private final HWPFDocument hwpfDocument;

    public DocImageExtractor(HWPFDocument hwpfDocument) {
        this.hwpfDocument = hwpfDocument;
    }

    @Override
    public List<Image> imageList() throws IOException {
        List<Image> imageList = new ArrayList<>();
        this.hwpfDocument.getPicturesTable().getAllPictures().forEach(picture -> {
            if (picture.getMimeType().equals("image/x-emf")) {
                imageList.add(new DocxEmfImage(picture));
            } else if (picture.getMimeType().equals("image/x-wmf")){
                imageList.add(new DocxWMFImage(picture));
            } else {
                imageList.add(new DocImage(picture));
            }
        });

        return imageList;
    }
}
