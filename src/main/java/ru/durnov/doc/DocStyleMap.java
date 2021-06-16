package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.StyleSheet;
import org.apache.poi.hwpf.usermodel.Paragraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class encapsulate map <styleId, style>.
 */
public class DocStyleMap {
    private final Map<Integer, String> styleMap = new HashMap<>();
    private final StyleSheet styleSheet;

    public DocStyleMap(HWPFDocument hwpfDocument){
        this.styleSheet = hwpfDocument.getStyleSheet();
        List<String> headerStyleNameList = new DocHeaderStyleList(styleSheet).headerList();
        for (int i = 0; i < headerStyleNameList.size(); i++){
            this.styleMap.put(i+1, headerStyleNameList.get(i));
        }
    }

    public boolean paragraphIsHeader(Paragraph paragraph){
        return this.styleMap.containsValue(
                this.styleSheet.getStyleDescription(
                        paragraph.getStyleIndex()
                ).getName()
        );
    }

    public Integer levelByParagraph(Paragraph paragraph){
        String styleName = this.styleSheet.getStyleDescription(
                paragraph.getStyleIndex()).getName();
        Set<Map.Entry<Integer, String>> entrySet = this.styleMap.entrySet();
        for (Map.Entry<Integer, String> entry : entrySet) {
            if (entry.getValue().equals(styleName)) return entry.getKey();
        }
        throw new IllegalArgumentException("cant't return level cause map doesn't content paragraph style");
    }
}
