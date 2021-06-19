package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import ru.durnov.chapters.Chapter;
import ru.durnov.chapters.Index;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocxContentChapter implements Chapter {
    private final int level;
    private final String content;
    private final static Pattern pattern = Pattern.compile("^(\\s*[0-9]+\\s*(\\.)*)+");
    private final String title;
    private final CTPageMar ctPageMar;


    public DocxContentChapter(int levelValue,
                              Index index,
                              List<IBodyElement> bodyElements,
                              DocxStyleMap docxStyleMap,
                              CTPageMar ctPageMar){
        this.ctPageMar = ctPageMar;
        this.level = levelValue;
        IBodyElement iBodyElement = bodyElements.get(index.currentIndex());
        if (! (iBodyElement instanceof XWPFParagraph)) throw new IllegalArgumentException(
                "Body element must be XWPFParagraph"
        );
        XWPFParagraph xwpfParagraph = (XWPFParagraph) iBodyElement;
        Matcher matcher = pattern.matcher(xwpfParagraph.getText());
        if (matcher.find()){
            this.title = matcher.group();
            this.content = new DocxChapterContentSetter(docxStyleMap, title, bodyElements, index, ctPageMar).content();
        } else {
            throw new IllegalArgumentException("Can't return content because xwpfParagraph is not chapter header");
        }
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
