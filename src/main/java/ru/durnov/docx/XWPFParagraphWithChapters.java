package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import ru.durnov.chapters.Chapter;
import ru.durnov.chapters.ChapterExtractor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XWPFParagraphWithChapters {
    private final XWPFParagraph xwpfParagraph;

    public XWPFParagraphWithChapters(XWPFParagraph xwpfParagraph) {
        this.xwpfParagraph = xwpfParagraph;
    }


    public Chapter chapter() {
        return null;
    }
}
