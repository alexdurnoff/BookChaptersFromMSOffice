package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class DocxContentChapterLevelSetter {
    private final DocxLevel docxLevel;
    private final XWPFParagraph xwpfParagraph;

    public DocxContentChapterLevelSetter(DocxLevel docxLevel, XWPFParagraph xwpfParagraph) {
        this.docxLevel = docxLevel;
        this.xwpfParagraph = xwpfParagraph;
    }

    public void setLevelValue() {

    }
}
