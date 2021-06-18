package ru.durnov.oldword;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import ru.durnov.chapters.Chapter;
import ru.durnov.chapters.Index;
import ru.durnov.doc.DocContentChapterChecker;
import ru.durnov.doc.DocStyleMap;
import ru.durnov.docx.DocxContentChapterChecker;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.List;

public class OldHeaderChapter implements Chapter {
    private final int level;
    private final String title;
    private final String content;
    private final int start;
    private final int stop;


    public OldHeaderChapter(int level, Index index,
                            HWPFDocument hwpfDocument,
                            List<Paragraph> paragraphList,
                            DocStyleMap docStyleMap,
                            DocContentChapterChecker checker) throws ParserConfigurationException, TransformerException {
        this.level = level;
        this.title = paragraphList.get(index.currentIndex()).text();
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
        return false;
    }

    @Override
    public String content() {
        return this.content;
    }
}
