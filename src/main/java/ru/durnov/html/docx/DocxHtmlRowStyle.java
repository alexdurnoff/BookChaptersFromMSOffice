package ru.durnov.html.docx;

import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.jsoup.nodes.Element;
import ru.durnov.html.RowStyle;

public class DocxHtmlRowStyle implements RowStyle {
    private final XWPFTableRow xwpfTableRow;

    public DocxHtmlRowStyle(XWPFTableRow xwpfTableRow) {
        this.xwpfTableRow = xwpfTableRow;
    }

    @Override
    public void applyToTableRowElement(Element element) {

    }
}
