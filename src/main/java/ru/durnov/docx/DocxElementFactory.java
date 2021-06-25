package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;

/*
TODO: красный цвет там, где у p-тэга бэкгроунд на auto.
Наверное, отступ по параметрам страницы надо убирать. В doc-версии этого нет.
 */
public class DocxElementFactory {
    private final IBodyElement bodyElement;
    private final CTSectPr ctSectPr;

    public DocxElementFactory(IBodyElement bodyElement){
        this.bodyElement = bodyElement;
        this.ctSectPr = null;
    }


    public DocxElementFactory(IBodyElement bodyElement, CTSectPr ctSectPr) {
        this.bodyElement = bodyElement;
        this.ctSectPr = ctSectPr;
    }

    public DocxContentElement docxContentElement(){
        if (bodyElement instanceof XWPFParagraph) return new DocxTextContentElement(bodyElement, ctSectPr);
        if (bodyElement instanceof XWPFTable) return new DocxTableContentElement(bodyElement, ctSectPr);
        throw new IllegalArgumentException("can't return DocxContentDocument because bodyElement is not instance of XWPFParagraph " +
                "and is not instance of XWPFTable");
    }
}
