package ru.durnov.html.doc;

import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.jsoup.nodes.Element;
import ru.durnov.html.PicturesElement;

public class DocChildElement {
    private final CharacterRun characterRun;
    private final PicturesTable picturesTable;


    public DocChildElement(CharacterRun characterRun, PicturesTable picturesTable) {
        this.characterRun = characterRun;
        this.picturesTable = picturesTable;
    }

    public void appendTo(Element element) {
        if (picturesTable.hasEscherPicture(characterRun)){
            new PicturesElement(characterRun, picturesTable).element().appendTo(element);
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
