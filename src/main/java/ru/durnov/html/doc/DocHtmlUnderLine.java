package ru.durnov.html.doc;

import org.apache.poi.hwpf.usermodel.CharacterRun;

public class DocHtmlUnderLine {
    private final int code;

    public DocHtmlUnderLine(CharacterRun characterRun) {
        this.code = characterRun.getUnderlineCode();
    }

    public String value() {
        if (this.code == 0) return "none";
        return "underline";
    }
}
