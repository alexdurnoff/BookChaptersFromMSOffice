package ru.durnov.oldword;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import ru.durnov.chapters.Chapter;
import ru.durnov.chapters.ContentChapterTitle;
import ru.durnov.chapters.Index;
import ru.durnov.doc.DocContentChapterChecker;
import ru.durnov.doc.DocStyleMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.List;
import java.util.regex.Pattern;

public class OldContentChapter implements Chapter {
    private final HWPFDocument hwpfDocument;
    private final int start;
    private final int stop;
    private final static Pattern pattern = Pattern.compile("^(\\s*[0-9]+\\s*(\\.)*)+");
    private final int level;
    private final String title;
    private final String content;

    public OldContentChapter(HWPFDocument hwpfDocument,
                             int start,
                             int stop,
                             int level,
                             String title) throws ParserConfigurationException, TransformerException {
        this.hwpfDocument = hwpfDocument;
        this.start = start;
        this.stop = stop;
        this.level = level;
        this.title = title;
        this.content = new OldContentSetter(hwpfDocument, start, stop).content();
    }

    public OldContentChapter(HWPFDocument hwpfDocument,
                             Index index,
                             int level,
                             List<Paragraph> paragraphList,
                             DocStyleMap docStyleMap,
                             DocContentChapterChecker checker
                             ) throws ParserConfigurationException, TransformerException {
        this.hwpfDocument = hwpfDocument;
        this.level = level;
        Paragraph paragraph = paragraphList.get(index.currentIndex());
        this.title = new ContentChapterTitle(paragraph.text()).title();
        Coordinates coordinates = new OldChapterCoordinates(
                hwpfDocument,
                index,
                docStyleMap,
                checker
        );
        this.start = coordinates.start();
        this.stop = coordinates.stop();
        this.content = new OldContentSetter(hwpfDocument, start, stop).content();
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
