package ru.durnov.doc;


import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Table;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import ru.durnov.chapters.Index;

import ru.durnov.oldword.OldContentSetter;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;


public class DocTableContentElement implements DocContentElement {
    private final HWPFDocument hwpfDocument;
    private final Table table;


    public DocTableContentElement(HWPFDocument hwpfDocument, Table table) {
        this.table = table;
        this.hwpfDocument = hwpfDocument;
    }

    @Override
    public Element element() throws ParserConfigurationException, TransformerException {
        Document document = Jsoup.parse(new OldContentSetter(hwpfDocument, table).content());
        Element element = document.getElementsByTag("table").get(0);
        return element;
    }
}
