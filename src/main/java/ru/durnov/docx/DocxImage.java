package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import ru.durnov.chapters.Image;

public class DocxImage implements Image {
    private final XWPFPictureData xwpfPictureData;

    public DocxImage(XWPFPictureData xwpfPictureData) {
        this.xwpfPictureData = xwpfPictureData;
    }

    @Override
    public String name() {
        return this.xwpfPictureData.getFileName();
    }

    @Override
    public byte[] asByteArray() {
        return this.xwpfPictureData.getData();
    }
}
