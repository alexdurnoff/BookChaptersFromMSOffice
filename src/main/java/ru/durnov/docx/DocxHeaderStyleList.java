package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
                if (style.getName().getVal().contains("heading")){
                    headerList.add(xwpfStyles.getStyle(style.getStyleId()));
                }
            });
        } catch (XmlException | IOException e) {
            System.out.println("exception catched in DocxHeaderList");
            e.printStackTrace();
        }
        return headerList
                .stream()
                .sorted(new XWPFStyleComparator())
                .collect(Collectors.toList());
    }
}
