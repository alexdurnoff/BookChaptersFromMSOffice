package ru.durnov.html.doc;

import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.jsoup.nodes.Element;
import ru.durnov.html.HtmlFontFamily;
import ru.durnov.html.HtmlFontSize;
import ru.durnov.html.HtmlFontColor;
import ru.durnov.html.docx.DocxHtmlUnderLine;

public class DocHtmlRunStyle {
    private final String key = "style";
    private final CharacterRun characterRun;

    public DocHtmlRunStyle(CharacterRun characterRun) {
        this.characterRun = characterRun;
    }

    public void applyToRunElement(Element element) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("font-family:")
                .append(new HtmlFontFamily(characterRun).value())
                .append(";");
        stringBuilder.append(' ');
        stringBuilder.append("font-size:")
                .append(new HtmlFontSize(characterRun)
                        .value()).append(";");
        stringBuilder.append(' ');
        stringBuilder.append("color:")
                .append(new HtmlFontColor(characterRun).value())
                .append(";");
        stringBuilder.append(' ');
        stringBuilder.append("text-decoration:")
                .append(new DocHtmlUnderLine(characterRun)
                        .value());
        element.attributes().put(key, stringBuilder.toString());
    }
}
