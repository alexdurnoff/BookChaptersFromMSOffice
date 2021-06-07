package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.durnov.chapters.Chapter;
import ru.durnov.chapters.Index;
import ru.durnov.chapters.Level;

import java.util.List;

public class DocxContentChapter implements Chapter {
    private final int level;
    private final String content;


    public DocxContentChapter(int levelValue, Index index, List<IBodyElement> bodyElements, DocxStyleMap docxStyleMap){
        this.level = levelValue;
        this.content = new DocxChapterContentSetter(docxStyleMap, title(), bodyElements, index).content();
    }

    @Override
    public String title() {
        return null;
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
