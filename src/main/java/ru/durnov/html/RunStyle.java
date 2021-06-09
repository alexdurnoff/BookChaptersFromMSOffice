package ru.durnov.html;

import org.apache.poi.wp.usermodel.CharacterRun;
import org.jsoup.nodes.Element;

public interface RunStyle {
    void applyToRunElement(Element element);
}
