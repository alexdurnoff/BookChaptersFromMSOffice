package ru.durnov.doc;

import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.jsoup.nodes.Element;
import ru.durnov.html.doc.DocChildElement;
import ru.durnov.html.doc.DocParagraphStyle;
import ru.durnov.html.docx.DocxParagraphStyle;
import ru.durnov.html.docx.HtmlDivStyle;

public class DocParagraphContentElement implements DocContentElement {
    private final ParagraphWithSection paragraphWithSection;
    private final PicturesTable picturesTable;

    public DocParagraphContentElement(ParagraphWithSection paragraphWithSection,
                                      PicturesTable picturesTable) {
        this.paragraphWithSection = paragraphWithSection;
        this.picturesTable = picturesTable;
    }

    @Override
    public Element element() {
        Element div  = new Element("div");
        new HtmlDivStyle(paragraphWithSection).applyTo(div);
        Element element = new Element("p");
        new DocParagraphStyle(paragraphWithSection).applyToParagraphElement(element);
        Paragraph paragraph = paragraphWithSection.paragraph();
        int count = paragraphWithSection.paragraph().numCharacterRuns();
        for (int i = 0; i < count; i++){
            CharacterRun characterRun = paragraph.getCharacterRun(i);
            new DocChildElement(characterRun, picturesTable).appendTo(element);
        }
        element.appendTo(div);
        return div;
    }
}
