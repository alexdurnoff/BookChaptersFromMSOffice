package ru.durnov.html.doc;

import org.jsoup.nodes.Element;
import ru.durnov.doc.ParagraphWithSection;
import ru.durnov.html.ParagraphStyleParameters;

public class DocParagraphStyle {
    private final ParagraphWithSection paragraphWithSection;

    public DocParagraphStyle(ParagraphWithSection paragraphWithSection) {
        this.paragraphWithSection = paragraphWithSection;
    }

    public void applyToParagraphElement(Element element) {
        new ParagraphStyleParameters(paragraphWithSection.paragraph()).applyToParagraphElement(element);
    }
}
