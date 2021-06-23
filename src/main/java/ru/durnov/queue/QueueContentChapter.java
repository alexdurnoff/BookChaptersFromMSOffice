package ru.durnov.queue;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import ru.durnov.chapters.Chapter;
import ru.durnov.chapters.NonHeaderChapterTitle;
import ru.durnov.doc.DocStyleMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class QueueContentChapter implements Chapter {
    private final int level;
    private final String content;
    private final String title;

    public QueueContentChapter(int level,
                               DocStyleMap docStyleMap,
                               HWPFDocument hwpfDocument,
                               Range range,
                               Ranges ranges,
                               Pictures pictures) throws ParserConfigurationException, TransformerException {
        if (! (range instanceof Paragraph)) throw new IllegalArgumentException(
                "Range must be paragraph"
        );
        Paragraph paragraph = (Paragraph) range;
        this.level = level;
        this.title = new NonHeaderChapterTitle(paragraph.text()).title();
        this.content = new QueueContentSetter(
                docStyleMap,
                ranges,
                pictures,
                hwpfDocument
        ).content();
    }

    @Override
    public String title() {
        return null;
    }

    @Override
    public int level() {
        return 0;
    }

    @Override
    public boolean inline() {
        return false;
    }

    @Override
    public String content() {
        return null;
    }
}
