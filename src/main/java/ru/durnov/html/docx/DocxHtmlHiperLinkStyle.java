package ru.durnov.html.docx;

import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jsoup.nodes.Element;
import ru.durnov.html.HtmlFontFamily;
import ru.durnov.html.HtmlFontSize;
import ru.durnov.html.RunStyle;

public class DocxHtmlHiperLinkStyle implements RunStyle {
    private final String key = "style";
    private final XWPFRun xwpfRun;

    public DocxHtmlHiperLinkStyle(XWPFRun xwpfRun) {
        this.xwpfRun = xwpfRun;
    }


    @Override
    public void applyToRunElement(Element element) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("font-family:").append(new HtmlFontFamily(xwpfRun).value()).append(";");
        stringBuilder.append(' ');
        stringBuilder.append("font-size:").append(new HtmlFontSize(xwpfRun).value()).append(";");
        stringBuilder.append(' ');
        stringBuilder.append("color").append("navy").append(";");
        stringBuilder.append(' ');
        stringBuilder.append("text-decoration:").append("underline");
        element.attributes().put(key, stringBuilder.toString());
    }
}
