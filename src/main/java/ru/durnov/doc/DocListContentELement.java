package ru.durnov.doc;

import org.jsoup.nodes.Element;
import ru.durnov.chapters.Index;

public class DocListContentELement implements DocContentElement {
    public DocListContentELement(ParagraphWithSection paragraphWithSection, Index index) {
    }

    @Override
    public Element element() {
        return null;
    }
}
