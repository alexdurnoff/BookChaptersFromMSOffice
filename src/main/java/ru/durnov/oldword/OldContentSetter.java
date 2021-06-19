package ru.durnov.oldword;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

public class OldContentSetter {
    private final int start;
    private final int stop;
    private final HWPFDocument hwpfDocument;
    private final Range range;



    public OldContentSetter(HWPFDocument hwpfDocument, int start, int stop) {
        this.start = start;
        this.stop = stop;
        this.hwpfDocument = hwpfDocument;
        this.range = new Range(start, stop, hwpfDocument);
    }

    public String content() throws ParserConfigurationException, TransformerException {
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        WordToHtmlConverter converter = new WordToHtmlConverter(document);
        converter.processDocumentPart(hwpfDocument,range);
        StringWriter stringWriter = new StringWriter();
        Transformer transformer = TransformerFactory.newInstance()
                .newTransformer();
        transformer.setOutputProperty( OutputKeys.INDENT, "yes" );
        transformer.setOutputProperty( OutputKeys.ENCODING, "utf-8" );
        transformer.setOutputProperty( OutputKeys.METHOD, "html" );
        transformer.transform(
                new DOMSource( converter.getDocument() ),
                new StreamResult( stringWriter ) );
        return stringWriter.toString().replace("<a", "<span").replace("</a>", "</span>");
    }
}
