package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class DocxTextContentElementTest {
    private final XWPFDocument xwpfDocument = new XWPFDocument();

    @Test
    void firstTestHowLookDocumentWithTextElements() throws IOException {
        XWPFParagraph xwpfParagraph = xwpfDocument.createParagraph();
        xwpfParagraph.createRun().setText("В суровую зимнюю пору");
        xwpfParagraph.createRun().setText("я из лесу вышел, был сильный мороз");
        xwpfParagraph.createRun().setText("Гляжу:");
        xwpfParagraph.createRun().setText(" поднимается медленно в гору лошадка");
        xwpfParagraph.createRun().setText(", везущая хвороста воз");
        DocxTextContentElement docxTextContentElement = new DocxTextContentElement(xwpfParagraph);
        Element element = docxTextContentElement.element();
        Files.writeString(Path.of("Test/firstTestDocxTextContentElement.html"), element.outerHtml());
    }

}