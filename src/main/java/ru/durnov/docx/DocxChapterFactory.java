package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.zwobble.mammoth.internal.styles.StyleMap;
import ru.durnov.chapters.Chapter;
import ru.durnov.chapters.ChapterFactory;
import ru.durnov.chapters.Index;
import ru.durnov.chapters.Level;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocxChapterFactory implements ChapterFactory {
    private final Level level;
    private final Index index;
    private final List<XWPFParagraph> paragraphs;
    private final DocxStyleMap styleMap;



    public DocxChapterFactory(Index index, Level level, List<XWPFParagraph> paragraphs) {
        this.index = index;
        this.level = level;
        this.paragraphs = paragraphs;
        this.styleMap = new DocxStyleMap(paragraphs);
    }

    @Override
    public Chapter chapter() {
        XWPFParagraph xwpfParagraph = paragraphs.get(index.currentIndex());
        if (styleMap.paragraphIsHeader(xwpfParagraph)){
            return new DocxHeaderChapter(styleMap.levelByParagraph(xwpfParagraph), xwpfParagraph);
        }
        if (new DocxContentChapterChecker(xwpfParagraph).isChapter()) {
            this.level.decrementLevel();
            return new DocxContentChapter(level, index, paragraphs);
        }
        throw new IllegalArgumentException("can't return Chapter because paragraph is not header and not start with number");
    }
}
