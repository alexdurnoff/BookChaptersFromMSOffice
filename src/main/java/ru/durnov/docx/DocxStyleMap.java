package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;
import ru.durnov.chapters.Level;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class encapsulate map <styleId, style>.
 */
public class DocxStyleMap {
    private final Map<Integer, String> styleMap = new HashMap<>();

    public DocxStyleMap(XWPFDocument xwpfDocument) {
        List<XWPFStyle> headerStyleList = new DocxHeaderStyleList(xwpfDocument).headerList();
        for (int i = 0; i < headerStyleList.size(); i++){
            this.styleMap.put(i + 1,headerStyleList.get(i).getStyleId());
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
        Set<Map.Entry<Integer, String>> entries = this.styleMap.entrySet();
        for (Map.Entry<Integer, String> entry : entries) {
            if (entry.getValue().equals(styleName)) return entry.getKey();
        }
        throw new IllegalArgumentException("cant't return level cause map doesn't content paragraph style");
    }
}
