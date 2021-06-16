package ru.durnov.doc;

import org.apache.poi.hwpf.model.StyleSheet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DocHeaderStyleList {
    private final StyleSheet styleSheet;
    private final List<String> headerList = new ArrayList<>();

    public DocHeaderStyleList(StyleSheet styleSheet) {
        this.styleSheet = styleSheet;
    }

    public List<String> headerList() {
        int styleNumber = styleSheet.numStyles();
        for (int i = 0; i < styleNumber; i++){
            String styleName = styleSheet.getStyleDescription(i).getName();
            if (styleName.contains("Заголовок")
                    || styleName.contains("Heading")
                    || styleName.contains("heading")){
                headerList.add(styleName);
            }
        }
        return this.headerList
                .stream()
                .sorted(String::compareToIgnoreCase)
                .collect(Collectors.toList());
    }
}
