package ru.durnov.docx;

import org.apache.poi.xwpf.converter.FileURIResolver;
import org.apache.poi.xwpf.converter.IURIResolver;
import org.apache.poi.xwpf.converter.IXWPFConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.converter.xhtml.XWPF2XHTMLConverter;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.junit.jupiter.api.Test;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtrRef;
import org.zwobble.mammoth.DocumentConverter;
import org.zwobble.mammoth.Result;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DocxChapterExtractorTest {

    @Test
    void testDocumentStructure() throws IOException, XmlException {
        String url = "Test/prikaz1.docx";
        InputStream inputStream = Files.newInputStream(Path.of(url));
        XWPFDocument xwpfDocument = new XWPFDocument(inputStream);
        List<IBodyElement> bodyElements = xwpfDocument.getBodyElements();
        bodyElements.forEach(bodyElement ->{
            System.out.println(bodyElement.getElementType());
        });
        XWPFStyles styles = xwpfDocument.getStyles();
        int styleNumbers = styles.getNumberOfStyles();
        for (int i = 1; i <= styleNumbers; i++){
            XWPFStyle style = styles.getStyle(String.valueOf(i));
            System.out.println(style.getName());
        }
        XWPFParagraph headerParagraph = null;
        List<XWPFParagraph> paragraphs = xwpfDocument.getParagraphs();
        for (XWPFParagraph paragraph : paragraphs) {
            if (paragraph.getText().contains("Общие положения")) headerParagraph = paragraph;
        }
        System.out.println(headerParagraph.getStyleID());
        List<XWPFRun> runs = headerParagraph.getRuns();
        System.out.println(runs.size());
        for (XWPFRun run : runs) {

        }
    }

    @Test
    void testConvertingFromDocxToHtml() throws IOException {
        InputStream in= new FileInputStream(new File("Test/prikaz1.docx"));
        XWPFDocument document = new XWPFDocument(in);


        XHTMLOptions options = new XHTMLOptions();
        //options.setURIResolver(new FileURIResolver(new File("word/media")));
        options.setURIResolver(IURIResolver.DEFAULT);
        //XHTMLOptions.create().URIResolver(new FileURIResolver(new File("word/media")));

        OutputStream out = new ByteArrayOutputStream();

        IXWPFConverter<XHTMLOptions> instance = XWPF2XHTMLConverter.getInstance();
        System.out.println(instance);
        System.out.println(document);
        System.out.println(options);
        instance.convert(document, out, options);
        String html=out.toString();
        System.out.println(html);
    }

    @Test
    public void testMammothConverter() throws IOException {
        DocumentConverter converter = new DocumentConverter();
        Result<String> result = converter.convertToHtml(new File("Test/prikaz1.docx"));
        String html = result.getValue(); // The generated HTML
        Set<String> warnings = result.getWarnings(); // Any warnings during conversion
        Files.newBufferedWriter(Path.of("Test/mammothConvertTiHtml.html")).write(html);
    }

}