package ru.durnov.doc;

import org.apache.poi.hwpf.usermodel.Paragraph;
import ru.durnov.chapters.Index;


public class DocElementFactory {
    private final ParagraphWithSection paragraphWithSection;
    private final Paragraph paragraph;
    private final Index index;

    public DocElementFactory(ParagraphWithSection paragraphWithSection, Index index) {
        this.paragraphWithSection = paragraphWithSection;
        this.paragraph = paragraphWithSection.paragraph();
        this.index = index;
    }


    public DocContentElement docContentElement() {
        if (paragraph.isInTable()){
            return new DocTableContentElement(
                    paragraphWithSection.section().getTable(paragraph),
                    index
            );
        }
        if (paragraph.isInList()) {
            return new DocListContentELement(
                    paragraphWithSection,
                    index
            );
        }
        return new DocParagraphContentElement(
                paragraph
        );
    }
}
