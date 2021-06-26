package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.Paragraph;
import ru.durnov.chapters.Chapter;
import ru.durnov.chapters.Index;
import ru.durnov.chapters.NonHeaderChapterTitle;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Deprecated
public class DocContentChapter implements Chapter {
    private final int level;
    private String content;
    private final String title;

    public DocContentChapter(int level,
                             Index index,
                             List<ParagraphWithSection> paragraphWithSectionList,
                             DocStyleMap docStyleMap,
                             PicturesTable picturesTable,
                             HWPFDocument hwpfDocument) throws ParserConfigurationException, TransformerException {
        this.level = level;
        Paragraph paragraph = paragraphWithSectionList.get(index.currentIndex()).paragraph();
        if (paragraph.isInTable()) throw new IllegalArgumentException("Paragraph should be not in Table!");
        this.title = new NonHeaderChapterTitle(paragraph.text()).title();
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
        return true;
    }

    @Override
    public String content() {
        return this.content;
    }
}
