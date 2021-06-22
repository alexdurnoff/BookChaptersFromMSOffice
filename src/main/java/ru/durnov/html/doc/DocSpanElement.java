package ru.durnov.html.doc;

import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.jsoup.nodes.Element;

public class DocSpanElement {
    private final CharacterRun characterRun;

    public DocSpanElement(CharacterRun characterRun) {
        this.characterRun = characterRun;
    }

    public Element element() {
        Element element = new Element("span");
        if (characterRun.isHighlighted()){
            new DocUElement(characterRun).element().appendTo(element);
        } else if (characterRun.isBold()) {
            new DocStrongElement(characterRun).element().appendTo(element);
        } else {
            element.appendText(characterRun.text().replace("\n", ""));
        }
        return null;
    }
}
