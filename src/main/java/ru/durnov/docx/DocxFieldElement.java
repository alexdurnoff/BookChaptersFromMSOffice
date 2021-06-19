package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFFieldRun;
import org.jsoup.nodes.Element;

public class DocxFieldElement {
    private final XWPFFieldRun xwpfFieldRun;

    public DocxFieldElement(XWPFFieldRun xwpfFieldRun) {
        this.xwpfFieldRun = xwpfFieldRun;
    }

    public void appendTo(Element element) {


    }
}
