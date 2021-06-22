package ru.durnov.doc;


import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.HWPFDocumentCore;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import ru.durnov.chapters.Index;

import ru.durnov.html.HtmlSkippingCellDetector;
import ru.durnov.html.docx.HtmlDivStyle;
import ru.durnov.html.table.SkippingCellDetector;



public class DocTableContentElement implements DocContentElement {
    private final ParagraphWithSection paragraphWithSection;
    private final Paragraph paragraph;
    private final Table table;
    private final SkippingCellDetector detector;
    private final Index index;

    public DocTableContentElement(ParagraphWithSection paragraphWithSection, Index index) {
        this.paragraphWithSection = paragraphWithSection;
        this.paragraph = paragraphWithSection.paragraph();
        this.table = paragraphWithSection.section().getTable(paragraph);
        this.detector = new HtmlSkippingCellDetector(table);
        this.index = index;
    }

    @Override
    public Element element()  {
        Element div  = new Element("div");
        new HtmlDivStyle(paragraphWithSection).applyTo(div);
        Element element = new Element("table");

        element.appendTo(div);
        return div;
    }
}
