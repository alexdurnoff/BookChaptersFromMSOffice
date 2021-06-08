package ru.durnov.html.docx;

import org.apache.poi.wp.usermodel.CharacterRun;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import ru.durnov.html.RunStyle;

public class DocxHtmlRunStyle implements RunStyle {
    private final String key = "style";
    private final XWPFRun xwpfRun;

    public DocxHtmlRunStyle(XWPFRun xwpfRun) {
        this.xwpfRun = xwpfRun;
    }

    @Override
    public void applyToRun() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("font-family:").append(xwpfRun.getFontName()).append(";");
        stringBuilder.append(' ');
        stringBuilder.append("font-size:").append(xwpfRun.getFontSize()).append(";");
        stringBuilder.append(' ');
        stringBuilder.append("color:").append(xwpfRun.getColor()).append(",");
    }
}
