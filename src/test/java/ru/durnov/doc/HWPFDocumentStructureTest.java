package ru.durnov.doc;

import lombok.SneakyThrows;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.converter.WordToHtmlUtils;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.model.StyleSheet;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import ru.durnov.oldword.OldContentSetter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HWPFDocumentStructureTest {

    @Test
    void test1() throws IOException, ParserConfigurationException, TransformerException {
        HWPFDocument hwpfDocument = new HWPFDocument(
                Files.newInputStream(Path.of("Test/prikaz1 with pictures.doc"))
        );
        Range range = hwpfDocument.getRange();
        int numCharacterRuns = range.numCharacterRuns();
        int numParagraphs = range.numParagraphs();
        Document document = Jsoup.parse(
                new OldContentSetter(
                        hwpfDocument,
                        range
                ).content()
        );
        System.out.println("paragraphs in hwpfDocument is " + numParagraphs);
        System.out.println("Runs in hwpfDOcument is " + numCharacterRuns);
        System.out.println("paragraphs in html is " + document.getElementsByTag("p").size());
        System.out.println("tables in html is " + document.getElementsByTag("table").size());

    }


}
