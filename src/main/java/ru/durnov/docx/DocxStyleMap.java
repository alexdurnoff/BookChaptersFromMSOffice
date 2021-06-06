package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.*;
import ru.durnov.chapters.Level;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class encapsulate map <styleId, style>.
 */
public class DocxStyleMap {
    private final Map<Level, String> styleMap = new HashMap<>();

    public DocxStyleMap(XWPFDocument xwpfDocument) {
        Level level = new Level(1);
        XWPFStyles styles = xwpfDocument.getStyles();
        int styleNumbers = styles.getNumberOfStyles();
        List<XWPFStyle> headerStyleList = new ArrayList<>();
        for (int i = 1; i <= styleNumbers; i++){
            XWPFStyle style = styles.getStyle(String.valueOf(i));
            if (style.getName().contains("Heading")) headerStyleList.add(style);
        }
        headerStyleList = headerStyleList
                .stream()
                .sorted(new XWPFStyleComparator())
                .collect(Collectors.toList());
        for (XWPFStyle xwpfStyle : headerStyleList) {
            this.styleMap.put(level, xwpfStyle.getName());
            level = new Level(level.currentLevel() + 1);
        }
    }

    public DocxStyleMap(List<IBodyElement> bodyElements){
        this(bodyElements.get(0).getBody().getXWPFDocument());
    }

    public boolean paragraphIsHeader(IBodyElement bodyElement) {
        if (! (bodyElement instanceof  XWPFParagraph)) return false;
        XWPFParagraph xwpfParagraph = (XWPFParagraph) bodyElement;
        return this.styleMap.containsValue(xwpfParagraph.getStyle());
    }

    public Integer levelByParagraph(XWPFParagraph xwpfParagraph) {
        String styleName = xwpfParagraph.getStyle();
        Set<Map.Entry<Level, String>> entries = this.styleMap.entrySet();
        for (Map.Entry<Level, String> entry : entries) {
            if (entry.getValue().equals(styleName)) return entry.getKey().currentLevel();
        }
        throw new IllegalArgumentException("cant't return level cause map doesn't content paragraph style");
    }
}
