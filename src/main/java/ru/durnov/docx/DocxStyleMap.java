package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.*;

import java.util.*;

/**
 * This class encapsulate map <styleId, style>.
 */
public class DocxStyleMap {
    private final Map<String, Integer> styleMap = new HashMap<>();

    public DocxStyleMap(XWPFDocument xwpfDocument) {
        List<XWPFStyle> headerStyleList = new DocxHeaderStyleList(xwpfDocument).headerList();
        for (XWPFStyle xwpfStyle : headerStyleList) {
            String styleName = xwpfStyle.getName();
            int level = new HeaderStyleLevel(styleName).level();
            this.styleMap.put(xwpfStyle.getStyleId(), level);
        }
    }

    public DocxStyleMap(List<IBodyElement> bodyElements){
        this(bodyElements.get(0).getBody().getXWPFDocument());
    }

    public boolean paragraphIsHeader(IBodyElement bodyElement) {
        if (! (bodyElement instanceof  XWPFParagraph)) return false;
        XWPFParagraph xwpfParagraph = (XWPFParagraph) bodyElement;
        return this.styleMap.containsKey(xwpfParagraph.getStyle());
    }

    public Integer levelByParagraph(XWPFParagraph xwpfParagraph) {
        String styleName = xwpfParagraph.getStyle();
        if (this.styleMap.containsKey(styleName)) return this.styleMap.get(styleName);
        throw new IllegalArgumentException("cant't return level cause map doesn't content paragraph style");
    }

    @Override
    public String toString() {
        return "DocxStyleMap{" +
                "styleMap=" + styleMap +
                '}';
    }
}
