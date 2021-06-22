package ru.durnov.html.doc;

import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.jsoup.nodes.Element;

public class DocUElement {
    private final CharacterRun characterRun;

    public DocUElement(CharacterRun characterRun) {
        this.characterRun = characterRun;
    }

    public Element element() {
        Element element = new Element("u");
        if (characterRun.isBold()){
            new DocStrongElement(characterRun).element().appendTo(element);
        } else {
            element.appendText(text());
        }
        return element;
    }

    protected String text() {
        return this.characterRun.text().replace("\n", "");
    }
}
