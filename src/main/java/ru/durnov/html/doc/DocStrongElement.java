package ru.durnov.html.doc;

import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.jsoup.nodes.Element;

public class DocStrongElement {
    private final CharacterRun characterRun;

    public DocStrongElement(CharacterRun characterRun) {
        this.characterRun = characterRun;
    }

    public Element element() {
        Element element = new Element("strong");
        if (characterRun.isHighlighted()){
            new DocUElement(characterRun).element().appendTo(element);
        } else {
            element.appendText(text());
        }
        return element;
    }

    protected String text() {
        return this.characterRun.text().replace("\n", "");
    }
}
