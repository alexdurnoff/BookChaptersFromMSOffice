package ru.durnov.html.doc;

import org.apache.poi.hwpf.usermodel.Paragraph;

public class DocAlignment {
    private final String alignment;

    public DocAlignment(Paragraph paragraph) {
        int value = paragraph.getFontAlignment();
        if (value == 0) {
            this.alignment = "justify";
        } else if (value == 1){
            this.alignment = "left";
        } else if (value == 3){
            this.alignment = "rigth";
        } else {
            this.alignment = "center";
        }
    }

    public String alignment(){
        return this.alignment;
    }
}
