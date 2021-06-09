package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.jsoup.nodes.Element;
import ru.durnov.html.CellStyle;

public class DocxTableCellStyle implements CellStyle {
    public DocxTableCellStyle(XWPFTableCell xwpfTableCell) {
    }

    @Override
    public void applyToTableCellElement(Element element) {

    }
}
