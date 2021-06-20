package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.jsoup.nodes.Element;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import ru.durnov.html.CellCoordinates;
import ru.durnov.html.HtmlSkippingCellDetector;
import ru.durnov.html.table.SkippingCellDetector;

public class DocxTableRowElement {
    private final XWPFTableRow xwpfTableRow;
    private final SkippingCellDetector detector;
    private final CTSectPr ctSectPr;


    public DocxTableRowElement(XWPFTableRow xwpfTableRow, SkippingCellDetector skippingCellDetector, CTSectPr ctSectPr){
        this.xwpfTableRow = xwpfTableRow;
        this.detector = skippingCellDetector;
        this.ctSectPr = ctSectPr;
    }

    public Element element() {
        Element rowElement = new Element("tr");
        this.xwpfTableRow.getTableCells().forEach(xwpfTableCell -> {
            if (! detector.cellMustBeSkipped(new CellCoordinates(xwpfTableCell))) {
                Element cellElement = new DocxTableCellElement(xwpfTableCell, ctSectPr).element();
                cellElement.appendTo(rowElement);
            }
        });
        return rowElement;
    }
}
