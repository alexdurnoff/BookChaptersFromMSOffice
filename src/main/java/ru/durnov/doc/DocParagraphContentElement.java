package ru.durnov.doc;

import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Section;
import org.jsoup.nodes.Element;
import ru.durnov.html.ParagraphStyleParameters;
import ru.durnov.html.doc.DocChildElement;
import ru.durnov.html.docx.HtmlDivStyle;

@Deprecated
public class DocParagraphContentElement implements DocContentElement {
    private final Paragraph paragraph;
    private final Section section;
    private final Pictures pictures;

    public DocParagraphContentElement(Paragraph paragraph,
                                      Section section,
                                      Pictures pictures) {
        this.paragraph = paragraph;
        this.section = section;
        this.pictures = pictures;
    }


    @Override
    public Element element() {
        Element div  = new Element("div");
        new HtmlDivStyle(section).applyTo(div);
        Element element = new Element("p");
        new ParagraphStyleParameters(paragraph).applyToParagraphElement(element);
        int count = paragraph.numCharacterRuns();
        for (int i = 0; i < count; i++){
            CharacterRun characterRun = paragraph.getCharacterRun(i);
            new DocChildElement(characterRun, pictures).appendTo(element);
        }
        element.appendTo(div);
        return div;
    }
}
