package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import ru.durnov.chapters.Image;
import ru.durnov.chapters.ImageExtractor;

import java.util.ArrayList;
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
            if (xwpfPictureData.getPictureType() == Document.PICTURE_TYPE_EMF){
                imageList.add(new EmfImage(xwpfPictureData));
            } else if (xwpfPictureData.getPictureType() == Document.PICTURE_TYPE_WMF){
                imageList.add(new WMFImage(xwpfPictureData));
            } else {
                imageList.add(new DocxImage(xwpfPictureData));
            }
        });
        return imageList;
    }
}
