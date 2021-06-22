package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.jsoup.nodes.Element;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;

public class DocxTableCellElement {
    private final XWPFTableCell xwpfTableCell;
    private final CTSectPr ctSectPr;


    public DocxTableCellElement(XWPFTableCell xwpfTableCell, CTSectPr ctSectPr) {
        this.xwpfTableCell = xwpfTableCell;
        this.ctSectPr = ctSectPr;

    }

    public Element element() {
        Element cellElement = new Element("td");
        new TableCellStyle(xwpfTableCell).applyToTableCellElement(cellElement);
        xwpfTableCell.getBodyElements().forEach(bodyElement -> {
            new DocxCellElementFactory(bodyElement)
                    .docxContentElement()
                    .element()
                    .appendTo(cellElement);
        });
        return cellElement;
    }
}
