package ru.durnov.doc;

import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Section;
@Deprecated
public class ParagraphWithSection {
    private final Paragraph paragraph;
    private final Section section;

    public ParagraphWithSection(Paragraph paragraph, Section section) {
        this.paragraph = paragraph;
        this.section = section;
    }

    public Paragraph paragraph(){
        return this.paragraph;
    }

    public Section section(){
        return this.section;
    }
}
