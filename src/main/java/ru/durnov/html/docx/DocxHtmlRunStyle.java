package ru.durnov.html.docx;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.jsoup.nodes.Element;
import ru.durnov.html.RunStyle;
import ru.durnov.html.WordHtmlParagraphAlign;

public class DocxHtmlRunStyle implements RunStyle {
    private final String key = "style";
    private final XWPFRun xwpfRun;

    public DocxHtmlRunStyle(XWPFRun xwpfRun) {
        this.xwpfRun = xwpfRun;
    }


    @Override
    public void applyToRunElement(Element element) {
        XWPFDocument xwpfDocument = xwpfRun.getDocument();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("font-family:").append(new DocxFontFamily(xwpfRun).value()).append(";");
        stringBuilder.append(' ');
        stringBuilder.append("font-size:").append(new DocxFontSize(xwpfRun).value()).append(";");
        stringBuilder.append(' ');
        stringBuilder.append("color:").append(new DocxHighLightColor(xwpfRun).value()).append(",");
        stringBuilder.append("text-decoration:").append(new DocxHtmlUnderLine(xwpfRun).value());
        element.attributes().put(key, stringBuilder.toString());
    }
}
