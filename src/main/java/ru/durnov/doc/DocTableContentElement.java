package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.converter.WordToHtmlUtils;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.jsoup.nodes.Element;
import org.w3c.dom.Document;
import ru.durnov.chapters.Index;
import ru.durnov.html.HtmlSkippingCellDetector;
import ru.durnov.html.table.HtmlTableStyle;
import ru.durnov.html.table.SkippingCellDetector;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DocTableContentElement implements DocContentElement {
    private final Table table;

    public DocTableContentElement(Table table, Index index) {
        this.table = table;
    }

    @Override
    public Element element() {
        Element element = new Element("table");

        return element;
    }
}
