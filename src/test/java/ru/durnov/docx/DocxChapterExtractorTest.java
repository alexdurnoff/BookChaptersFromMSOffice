package ru.durnov.docx;

import org.apache.poi.xwpf.converter.IXWPFConverter;
import org.apache.poi.xwpf.converter.Options;
import org.apache.poi.xwpf.converter.xhtml.*;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.impl.schema.SchemaTypeImpl;
import org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl;
import org.junit.jupiter.api.Test;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocDefaults;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrDefault;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTPPrDefaultImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTStylesImpl;
import org.zwobble.mammoth.DocumentConverter;
import org.zwobble.mammoth.Result;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


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
    void testConvertingFromDocxToHtml() throws IOException, XmlException {
        InputStream in= new FileInputStream(new File("Test/prikaz1.docx"));
        XWPFDocument document = new XWPFDocument(in);
        CTDocDefaults docDefaults = document.getStyle().getDocDefaults();
        CTPPrDefault ctpPrDefault = docDefaults.addNewPPrDefault();
        System.out.println(docDefaults.getPPrDefault());
        document.getStyle().setDocDefaults(docDefaults);
        System.out.println(document.getStyle().getDocDefaults().getPPrDefault());
        IXWPFConverter<XHTMLOptions> converter = XWPF2XHTMLConverter.getInstance();
        XHTMLOptions xhtmlOptions = new XHTMLOptions();
        xhtmlOptions.setIndent(1);
        xhtmlOptions.setGenerateCSSComments(false);
        //converter.convert(document, new FileOutputStream("Test/prikaz1.html"), null);
        //Убрал последнюю строку. Пока что тест падает.
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