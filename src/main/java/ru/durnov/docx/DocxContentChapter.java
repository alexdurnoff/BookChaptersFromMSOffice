package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import ru.durnov.chapters.Chapter;
import ru.durnov.chapters.Index;
import ru.durnov.chapters.NonHeaderChapterTitle;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocxContentChapter implements Chapter {
    private final int level;
    private final String content;
    private final String title;


    public DocxContentChapter(int levelValue,
                              Index index,
                              List<IBodyElement> bodyElements,
                              DocxStyleMap docxStyleMap,
                              CTSectPr ctSectPr){
        this.level = levelValue;
        IBodyElement iBodyElement = bodyElements.get(index.currentIndex());
        if (! (iBodyElement instanceof XWPFParagraph)) throw new IllegalArgumentException(
                "Body element must be XWPFParagraph"
        );
        XWPFParagraph xwpfParagraph = (XWPFParagraph) iBodyElement;
        this.title = new NonHeaderChapterTitle(xwpfParagraph.getText()).title();
        this.content = new DocxChapterContentSetter(
                docxStyleMap,
                title,
                bodyElements,
                index,
                ctSectPr
        ).content();
    }

    @Override
    public String title() {
        return title;
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
