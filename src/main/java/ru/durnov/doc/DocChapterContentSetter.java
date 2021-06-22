package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.jsoup.nodes.Document;
import ru.durnov.chapters.Index;
import ru.durnov.docx.DocxContentChapterChecker;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.List;

public class DocChapterContentSetter {
    private final DocStyleMap docStyleMap;
    private final String title;
    private final List<ParagraphWithSection> paragraphWithSectionList;
    private final Index index;
    private final DocContentChapterChecker checker = new DocContentChapterChecker();
    private final Document document;
    private final PicturesTable picturesTable;
    private final HWPFDocument hwpfDocument;



    public DocChapterContentSetter(DocStyleMap docStyleMap,
                                   String title,
                                   List<ParagraphWithSection> paragraphWithSectionList,
                                   Index index,
                                   PicturesTable picturesTable,
                                   HWPFDocument hwpfDocument) {
        this.docStyleMap = docStyleMap;
        this.index = index;
        this.paragraphWithSectionList = paragraphWithSectionList;
        this.title = title;
        this.document = new Document("/tmp/" + this.title + ".html");
        this.picturesTable = picturesTable;
        this.hwpfDocument = hwpfDocument;
    }

    public String content() throws ParserConfigurationException, TransformerException {
        ParagraphWithSection paragraphWithSection = this.paragraphWithSectionList.get(index.currentIndex());
        Paragraph paragraph = paragraphWithSection.paragraph();
        do {
            document.appendChild(
                    new DocElementFactory(paragraphWithSection, index, picturesTable, hwpfDocument)
                    .docContentElement()
                    .element()
            );
            index.incrementIndex();;
            if (index.currentIndex() == paragraphWithSectionList.size()) break;
            paragraphWithSection = this.paragraphWithSectionList.get(index.currentIndex());
            paragraph = paragraphWithSection.paragraph();
        } while (! this.docStyleMap.paragraphIsHeader(paragraph) &&
                ! checker.isChapter(paragraph));
        return document.outerHtml();
    }
}
