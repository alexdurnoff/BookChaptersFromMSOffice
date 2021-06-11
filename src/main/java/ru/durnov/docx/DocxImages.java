package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import ru.durnov.chapters.ImageExtractor;
import ru.durnov.chapters.Images;

public class DocxImages implements Images {
    private final XWPFDocument xwpfDocument;

    public DocxImages(XWPFDocument xwpfDocument) {
        this.xwpfDocument = xwpfDocument;
    }


    @Override
    public ImageExtractor imageExtractor() {
        return new DocxImageExtractor(this.xwpfDocument);
    }
}
