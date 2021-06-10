package ru.durnov.docx;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Test
    void testRegularExpression(){
        String str = "1. Настоящие Правила работы с персоналом в организациях электроэнергетики Российской Федерации";
        String expression = "^(\\s*[0-9]+\\s*(\\.)*)+";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(str);
        Assertions.assertTrue(matcher.find());
        Assertions.assertEquals(matcher.group(), "1.");
    }

    @Test
    void testSourceStringWith2Dotes(){
        String str = "1.1.2 Настоящие Правила работы с персоналом в организациях электроэнергетики Российской Федерации";
        String expression = "^(\\s*[0-9]+\\s*(\\.)*)+";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(str);
        Assertions.assertTrue(matcher.find());
        Assertions.assertEquals(matcher.group(), "1.1.2 ");
    }

    @Test
    void testSourceStringWith2SpaceAfterNumber(){
        String str = "2. Требования Правил распространяются на следующие организации";
        String expression = "^(\\s*[0-9]+\\s*(\\.)*)+";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(str);
        Assertions.assertTrue(matcher.find());
        Assertions.assertEquals(matcher.group(), "2.");
    }

}