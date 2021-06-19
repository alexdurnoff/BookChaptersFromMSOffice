package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;

public class DocxElementFactory {
    private final IBodyElement bodyElement;
    private final CTPageMar ctPageMar;

    public DocxElementFactory(IBodyElement bodyElement){
        this.bodyElement = bodyElement;
        this.ctPageMar = null;
    }


    public DocxElementFactory(IBodyElement bodyElement, CTPageMar ctPageMar) {
        this.bodyElement = bodyElement;
        this.ctPageMar = ctPageMar;
    }

    public DocxContentElement docxContentElement(){
        if (bodyElement instanceof XWPFParagraph) return new DocxTextContentElement(bodyElement, ctPageMar);
        if (bodyElement instanceof XWPFTable) return new DocxTableContentElement(bodyElement, ctPageMar);
        throw new IllegalArgumentException("can't return DocxContentDocument because bodyElement is not instance of XWPFParagraph " +
                "and is not instance of XWPFTable");
    }
}
