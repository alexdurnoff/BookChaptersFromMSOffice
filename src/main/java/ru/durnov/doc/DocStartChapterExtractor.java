package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Section;
import org.jsoup.nodes.Document;
import ru.durnov.chapters.Index;
import ru.durnov.chapters.StartChapter;
import ru.durnov.chapters.StartChapterExtractor;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.List;
@Deprecated
public class DocStartChapterExtractor implements StartChapterExtractor {
    private final List<ParagraphWithSection> paragraphsWithSectionsList;
    private final DocStyleMap docStyleMap;
    private final Index index;
    private final PicturesTable picturesTable;
    private final HWPFDocument hwpfDocument;

    public DocStartChapterExtractor(List<ParagraphWithSection> paragraphsWithSectionsList,
                                    DocStyleMap docStyleMap,
                                    Index index,
                                    PicturesTable picturesTable,
                                    HWPFDocument hwpfDocument) {
        this.paragraphsWithSectionsList = paragraphsWithSectionsList;
        this.docStyleMap = docStyleMap;
        this.index = index;
        this.picturesTable = picturesTable;
        this.hwpfDocument = hwpfDocument;
    }


    @Override
    public StartChapter startChapter() throws ParserConfigurationException, TransformerException {
        Document document = new Document("/tmp/" + "Начало документа" + ".html");
        ParagraphWithSection paragraphWithSection = this.paragraphsWithSectionsList
                .get(index.currentIndex());
        Paragraph paragraph = paragraphWithSection.paragraph();
        while ( ! this.docStyleMap.paragraphIsHeader(paragraph)){
            document.appendChild(
                    new DocElementFactory(paragraphWithSection, index, picturesTable, hwpfDocument)
                    .docContentElement()
                    .element()
            );
            index.incrementIndex();
            if (index.currentIndex() == this.paragraphsWithSectionsList.size()) break;
            paragraphWithSection = this.paragraphsWithSectionsList.get(index.currentIndex());
            paragraph = paragraphWithSection.paragraph();
        }
        return new StartChapter(document.html());
    }
}
