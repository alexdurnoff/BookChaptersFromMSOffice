package ru.durnov.html.doc;

import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.jsoup.nodes.Element;
import ru.durnov.html.PicturesElement;
import ru.durnov.doc.Pictures;

public class DocChildElement {
    private final CharacterRun characterRun;
    private final Pictures pictures;


    public DocChildElement(CharacterRun characterRun, Pictures pictures) {
        this.characterRun = characterRun;
        this.pictures = pictures;
    }

    public void appendTo(Element element) {
        if (pictures.paragraphHasPicture(characterRun)){
            new PicturesElement(pictures).element().appendTo(element);
        } else {
            Element child = new DocSpanElement(characterRun).element();
            new DocHtmlRunStyle(characterRun).applyToRunElement(child);
            child.appendTo(element);
            if (characterRun.text().endsWith("\n")) {
                new Element("br").appendTo(element);
            }
        }

    }
}
