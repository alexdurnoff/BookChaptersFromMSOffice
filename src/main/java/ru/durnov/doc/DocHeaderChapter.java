package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.jsoup.nodes.Document;
import ru.durnov.chapters.Chapter;
import ru.durnov.chapters.Index;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.List;

public class DocHeaderChapter implements Chapter {
    private final int level;
    private final String title;
    private final String content;


    public DocHeaderChapter(int level,
                            Index index,
                            List<ParagraphWithSection> paragraphWithSectionList,
                            DocStyleMap docStyleMap,
                            PicturesTable picturesTable,
                            HWPFDocument hwpfDocument) throws ParserConfigurationException, TransformerException {
        this.level = level;
        ParagraphWithSection paragraphWithSection = paragraphWithSectionList.get(index.currentIndex());
        this.title = paragraphWithSection.paragraph().text();
        this.content = new DocChapterContentSetter(
                docStyleMap,
                title,
                paragraphWithSectionList,
                index,
                picturesTable,
                hwpfDocument
        ).content();
    }

    @Override
    public String title() {
        return this.title;
    }

    @Override
    public int level() {
        return this.level;
    }

    @Override
    public boolean inline() {
        return false;
    }

    @Override
    public String content() {


        return this.content;
    }
}
