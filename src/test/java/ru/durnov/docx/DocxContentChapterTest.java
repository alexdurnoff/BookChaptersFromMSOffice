package ru.durnov.docx;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class DocxContentChapterTest {

    @Test
    void testCreateNewHtmlDocument() throws IOException {
        Document document = new Document("Test/testCreateHtmlDocument.html");
        document.appendElement("body");
        Element body = document.body();
        Element element = new Element("p");
        element.appendText("Привет, Дурнов!");
        body.appendChild(element);
        Files.writeString(Path.of(document.baseUri()), document.outerHtml());
    }

}