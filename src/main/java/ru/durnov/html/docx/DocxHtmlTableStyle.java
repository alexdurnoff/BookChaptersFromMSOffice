package ru.durnov.html.docx;

import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.jsoup.nodes.Element;
import ru.durnov.html.TableStyle;

public class DocxHtmlTableStyle implements TableStyle {
    private final XWPFTable xwpfTable;

    public DocxHtmlTableStyle(XWPFTable xwpfTable) {
        this.xwpfTable = xwpfTable;
    }

    @Override
    public void applyToTableElement(Element element) {

    }
}
