package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.jsoup.nodes.Element;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import ru.durnov.html.HtmlSkippingCellDetector;
import ru.durnov.html.docx.HtmlDivStyle;
import ru.durnov.html.table.HtmlTableStyle;
import ru.durnov.html.table.SkippingCellDetector;

public class DocxTableContentElement implements DocxContentElement {
    private final XWPFTable xwpfTable;
    private final SkippingCellDetector detector;
    private final CTSectPr ctSectPr;

    public DocxTableContentElement(IBodyElement bodyElement, CTSectPr ctSectPr){
        if (! (bodyElement instanceof  XWPFTable)) throw new IllegalArgumentException("IBobyElement must be instance of XWPFTable");
        this.xwpfTable = (XWPFTable) bodyElement;
        this.detector = new HtmlSkippingCellDetector(xwpfTable);
        this.ctSectPr = ctSectPr;
    }




    @Override
    public Element element() {
        Element div  = new Element("div");
        new HtmlDivStyle(ctSectPr).applyTo(div);
        Element element = new Element("table");
        new HtmlTableStyle(xwpfTable).applyToTableElement(element);
        xwpfTable.getRows().forEach(xwpfTableRow -> {
            Element rowElement = new DocxTableRowElement(xwpfTableRow, detector, ctSectPr).element();
            rowElement.appendTo(element);
        });
        element.appendTo(div);
        return div;
    }
}
