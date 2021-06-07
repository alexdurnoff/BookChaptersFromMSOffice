package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;

public class TestStyleUtils {
    private final XWPFDocument xwpfDocument;
    private int styleCounter=0;
    private final XWPFStyles xwpfStyles;

    public TestStyleUtils(XWPFDocument xwpfDocument) {
        this.xwpfDocument = xwpfDocument;
        this.xwpfStyles = this.xwpfDocument.createStyles();
    }

    public void addHeaderStyleWithGeneratedNumber(){
        CTStyle ctStyle = CTStyle.Factory.newInstance();
        ctStyle.setStyleId(String.valueOf(++styleCounter));
        CTString ctString = CTString.Factory.newInstance();
        ctString.setVal("Heading " + styleCounter);
        ctStyle.setName(ctString);
        XWPFStyle xwpfStyle = new XWPFStyle(ctStyle);
        XWPFStyles styles = xwpfDocument.createStyles();
        styles.addStyle(xwpfStyle);
    }

    public void addStyleByNameWithGeneratedNumber(String name){
        CTStyle ctStyle = CTStyle.Factory.newInstance();
        ctStyle.setStyleId(String.valueOf(++styleCounter));
        CTString ctString = CTString.Factory.newInstance();
        ctString.setVal(name);
        ctStyle.setName(ctString);
        XWPFStyle xwpfStyle = new XWPFStyle(ctStyle);
        XWPFStyles styles = xwpfDocument.createStyles();
        styles.addStyle(xwpfStyle);
    }
}
