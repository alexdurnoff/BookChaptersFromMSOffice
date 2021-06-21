package ru.durnov.docx;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class DocxHeaderStyleList {
    private final XWPFDocument xwpfDocument;

    public DocxHeaderStyleList(XWPFDocument xwpfDocument) {
        this.xwpfDocument = xwpfDocument;
    }

    public List<XWPFStyle> headerList() {
        XWPFStyles xwpfStyles = xwpfDocument.getStyles();
        List<XWPFStyle> headerList = new ArrayList<>();
        try {
            CTStyles styles = xwpfDocument.getStyle();
            styles.getStyleList().forEach(style -> {
                if (style.getName() != null) {
                    String styleName = style.getName().getVal();
                    if (styleName.contains("Heading") || styleName.contains("heading") || styleName.contains("Заголовок")){
                        headerList.add(xwpfStyles.getStyle(style.getStyleId()));
                    }
                }
            });
        } catch (XmlException | IOException | NullPointerException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return headerList
                .stream()
                .sorted(new XWPFStyleComparator())
                .collect(Collectors.toList());
    }
}
