package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.StyleSheet;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import ru.durnov.docx.HeaderStyleLevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class encapsulate map <styleId, style>.
 */
public class DocStyleMap {
    private final Map<String, Integer> styleMap = new HashMap<>();
    private final StyleSheet styleSheet;

    public DocStyleMap(HWPFDocument hwpfDocument){
        this.styleSheet = hwpfDocument.getStyleSheet();
        List<String> headerStyleNameList = new DocHeaderStyleList(styleSheet).headerList();
        for (String styleName : headerStyleNameList) {
            int level = new HeaderStyleLevel(styleName).level();
            this.styleMap.put(styleName, level);
        }
    }

    public boolean paragraphIsHeader(Range range){
        if (! (range instanceof Paragraph)) return false;
        Paragraph paragraph = (Paragraph) range;
        return this.styleMap.containsKey(
                this.styleSheet.getStyleDescription(
                        paragraph.getStyleIndex()
                ).getName()
        );
    }

    public Integer levelByParagraph(Paragraph paragraph){
        String styleName = this.styleSheet.getStyleDescription(
                paragraph.getStyleIndex()).getName();
        if (this.styleMap.containsKey(styleName)) return this.styleMap.get(styleName);
        System.out.println(paragraph.text());
        throw new IllegalArgumentException("cant't return level cause map doesn't content paragraph style");
    }

    @Override
    public String toString() {
        return "DocStyleMap{" +
                "styleMap=" + styleMap +
                '}';
    }
}
