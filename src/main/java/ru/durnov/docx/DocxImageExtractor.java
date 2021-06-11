package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import ru.durnov.chapters.Image;
import ru.durnov.chapters.ImageExtractor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DocxImageExtractor implements ImageExtractor {
    private final XWPFDocument xwpfDocument;

    public DocxImageExtractor(XWPFDocument xwpfDocument) {
        this.xwpfDocument = xwpfDocument;
    }

    @Override
    public List<Image> imageList() {
        List<Image> imageList = new ArrayList<>();
        this.xwpfDocument.getAllPackagePictures().forEach(xwpfPictureData -> {
            imageList.add(new DocxImage(xwpfPictureData));
        });
        return imageList;
    }
}
