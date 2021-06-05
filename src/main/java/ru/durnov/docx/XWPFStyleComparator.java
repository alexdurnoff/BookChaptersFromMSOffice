package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFStyle;

public class XWPFStyleComparator implements java.util.Comparator<XWPFStyle> {

    @Override
    public int compare(XWPFStyle o1, XWPFStyle o2) {
        return String.CASE_INSENSITIVE_ORDER.compare(o1.getName(), o2.getName());
    }
}
