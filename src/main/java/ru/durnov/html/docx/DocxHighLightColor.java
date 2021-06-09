package ru.durnov.html.docx;

import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHighlightColor;

public class DocxHighLightColor {
    private final STHighlightColor.Enum stHighlightColor;


    public DocxHighLightColor(XWPFRun xwpfRun) {
        this.stHighlightColor = xwpfRun.getTextHightlightColor();
    }

    public String value() {
        if (stHighlightColor == STHighlightColor.BLACK) return "black";
        if (stHighlightColor == STHighlightColor.BLUE) return "blue";
        if (stHighlightColor == STHighlightColor.CYAN) return "cyan";
        if (stHighlightColor == STHighlightColor.GREEN) return "green";
        if (stHighlightColor == STHighlightColor.MAGENTA) return "magenta";
        if (stHighlightColor == STHighlightColor.RED) return "red";
        if (stHighlightColor == STHighlightColor.YELLOW) return "yellow";
        if (stHighlightColor == STHighlightColor.WHITE) return "white";
        if (stHighlightColor == STHighlightColor.DARK_BLUE) return "darkBlue";
        if (stHighlightColor == STHighlightColor.DARK_CYAN) return "darkCyan";
        if (stHighlightColor == STHighlightColor.DARK_GREEN) return "darkGreen";
        if (stHighlightColor == STHighlightColor.DARK_MAGENTA) return "darkMagenta";
        if (stHighlightColor == STHighlightColor.DARK_RED) return "darkRed";
        if (stHighlightColor == STHighlightColor.DARK_YELLOW) return "darkYellow";
        if (stHighlightColor == STHighlightColor.DARK_GRAY) return "darkGray";
        if (stHighlightColor == STHighlightColor.LIGHT_GRAY) return "lightGray";
        return "none";
    }
}
