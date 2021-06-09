package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.IBodyElement;
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
    private final List<IBodyElement> bodyElements;
    private final DocxStyleMap docxStyleMap;




    public DocxChapterFactory(Index index, DocxStyleMap docxStyleMap, List<IBodyElement> bodyElements){
        this.index = index;
        this.docxStyleMap = docxStyleMap;
        this.level = new Level(docxStyleMap);
        this.bodyElements = bodyElements;
    }

    @Override
    public Chapter chapter() {
        if (bodyElements.get(index.currentIndex()) instanceof XWPFParagraph) {
            XWPFParagraph xwpfParagraph = (XWPFParagraph) bodyElements.get(index.currentIndex());
            if (docxStyleMap.paragraphIsHeader(xwpfParagraph)){
                return new DocxHeaderChapter(level.levelByParagraph(xwpfParagraph), xwpfParagraph);
            }
            if (new DocxContentChapterChecker().isChapter(xwpfParagraph)) {
                return new DocxContentChapter(level.levelByParagraph(xwpfParagraph), index, bodyElements, docxStyleMap);
            }
            throw new IllegalArgumentException("can't return Chapter because paragraph is not header and not start with number");
        } else {
            throw new IllegalArgumentException("can't return Chapter because bodyElement is not XWPFParagraph");
        }
    }
}
