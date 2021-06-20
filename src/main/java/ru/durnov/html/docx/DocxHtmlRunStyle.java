package ru.durnov.html.docx;

import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jsoup.nodes.Element;
import ru.durnov.html.RunStyle;

public class DocxHtmlRunStyle implements RunStyle {
    private final String key = "style";
    private final XWPFRun xwpfRun;

    public DocxHtmlRunStyle(XWPFRun xwpfRun) {
        this.xwpfRun = xwpfRun;
    }


    @Override
    public void applyToRunElement(Element element) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("font-family:").append(new DocxFontFamily(xwpfRun).value()).append(";");
        stringBuilder.append(' ');
        stringBuilder.append("font-size:").append(new DocxFontSize(xwpfRun).value()).append(";");
        stringBuilder.append(' ');
        stringBuilder.append("color:").append(new DocxFontColor(xwpfRun).value()).append(";");
        stringBuilder.append(' ');
        stringBuilder.append("text-decoration:").append(new DocxHtmlUnderLine(xwpfRun).value());
        element.attributes().put(key, stringBuilder.toString());
    }
}
