package ru.durnov.queue;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import ru.durnov.chapters.Chapter;
import ru.durnov.doc.DocStyleMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class QueueHeaderChapter implements Chapter {
    private final int level;
    private final String title;
    private final String content;


    public QueueHeaderChapter(int level,
                              DocStyleMap docStyleMap,
                              HWPFDocument hwpfDocument,
                              Range range,
                              Ranges ranges,
                              Pictures pictures) throws ParserConfigurationException, TransformerException {
        if (! (range instanceof Paragraph)) throw new IllegalArgumentException(
                "Range must be paragraph"
        );
        Paragraph paragraph = (Paragraph) range;
        this.title = paragraph.text();
        this.level = level;
        this.content = new QueueContentSetter(
                docStyleMap,
                ranges,
                pictures,
                hwpfDocument
        ).content();
    }

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
