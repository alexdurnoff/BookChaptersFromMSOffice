package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.durnov.chapters.Chapter;
import ru.durnov.chapters.Index;
import ru.durnov.chapters.Level;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocxContentChapter implements Chapter {
    private final int level;
    private final String content;
    private final static Pattern pattern = Pattern.compile("^\\s*([0-9])\\s*(.)+$");
    private final String title;


    public DocxContentChapter(int levelValue, Index index, List<IBodyElement> bodyElements, DocxStyleMap docxStyleMap){
        this.level = levelValue;
        this.content = new DocxChapterContentSetter(docxStyleMap, title(), bodyElements, index).content();
        IBodyElement iBodyElement = bodyElements.get(0);
        if (! (iBodyElement instanceof XWPFParagraph)) throw new IllegalArgumentException(
                "Body element must be XWPFParagraph"
        );
        XWPFParagraph xwpfParagraph = (XWPFParagraph) iBodyElement;
        String firstString = xwpfParagraph.getText();
        Matcher matcher = pattern.matcher(firstString);
        this.title = matcher.group(1);
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
