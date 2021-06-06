package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;

public class DocxElementFactory {
    private final IBodyElement bodyElement;

    public DocxElementFactory(IBodyElement bodyElement) {
        this.bodyElement = bodyElement;
    }

    public DocxContentElement docxContentElement(){
        if (bodyElement instanceof XWPFParagraph) return new DocxTextContentElement(bodyElement);
        if (bodyElement instanceof XWPFTable) return new DocxTableTextContentElement(bodyElement);
        throw new IllegalArgumentException("can't return DocxContentDocument because bodyElement is not instance of XWPFParagraph " +
                "and is not instance of XWPFTable");
    }
}
