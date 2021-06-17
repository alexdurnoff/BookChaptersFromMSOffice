package ru.durnov.doc;

import org.apache.poi.hwpf.usermodel.Paragraph;
import ru.durnov.chapters.Chapter;
import ru.durnov.chapters.Index;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocContentChapter implements Chapter {
    private final int level;
    private final String content;
    private final static Pattern pattern = Pattern.compile("^(\\s*[0-9]+\\s*(\\.)*)+");
    private final String title;



    public DocContentChapter(int level,
                             Index index,
                             List<ParagraphWithSection> paragraphWithSectionList,
                             DocStyleMap docStyleMap) {
        this.level = level;
        Paragraph paragraph = paragraphWithSectionList.get(index.currentIndex()).paragraph();
        if (paragraph.isInTable()) throw new IllegalArgumentException("Paragraph should be not in Table!");
        Matcher matcher = pattern.matcher(paragraph.text());
        if (matcher.find()){
            this.title = matcher.group();
            this.content = new DocChapterContentSetter(
                    docStyleMap,
                    title,
                    paragraphWithSectionList,
                    index
            ).content();
        } else {
            throw new IllegalArgumentException("Can't return content because paragraph is not chapter header");
        }
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
        return true;
    }

    @Override
    public String content() {
        return this.content;
    }
}
