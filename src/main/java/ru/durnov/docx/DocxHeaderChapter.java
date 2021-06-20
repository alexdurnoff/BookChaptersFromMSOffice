package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import ru.durnov.chapters.Chapter;
import ru.durnov.chapters.Index;

import java.util.List;

public class DocxHeaderChapter implements Chapter {
    private final int level;
    private final String title;
    private final String content;


    public DocxHeaderChapter(int levelValue,
                             Index index,
                             List<IBodyElement> bodyElements,
                             DocxStyleMap docxStyleMap,
                             CTSectPr ctSectPr) {
        this.level = levelValue;
        IBodyElement iBodyElement = bodyElements.get(index.currentIndex());
        if (! (iBodyElement instanceof XWPFParagraph)) throw new IllegalArgumentException(
                "Body element must be XWPFParagraph"
        );
        this.title = ((XWPFParagraph) iBodyElement).getText();
        this.content = new DocxChapterContentSetter(docxStyleMap, title, bodyElements, index, ctSectPr).content();
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
