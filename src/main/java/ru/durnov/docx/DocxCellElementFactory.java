package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;

public class DocxCellElementFactory {
    private final IBodyElement bodyElement;
    private final CTSectPr ctSectPr;

    public DocxCellElementFactory(IBodyElement bodyElement){
        this.bodyElement = bodyElement;
        this.ctSectPr = null;
    }


    public DocxCellElementFactory(IBodyElement bodyElement, CTSectPr ctSectPr) {
        this.bodyElement = bodyElement;
        this.ctSectPr = ctSectPr;
    }

    public DocxContentElement docxContentElement(){
        if (bodyElement instanceof XWPFParagraph) return new DocxCellContentElement(bodyElement);
        if (bodyElement instanceof  XWPFTable) return new DocxTableContentElement(bodyElement, ctSectPr);
        throw new IllegalArgumentException("can't return DocxContentDocument because bodyElement is not instance of XWPFParagraph " +
                "and is not instance of XWPFTable");
    }
}
