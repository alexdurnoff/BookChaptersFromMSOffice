package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.util.Comparator;

/**
 * По задумке класс будет сравннивать уровни двух параграфов.
 * По результату сравнения Level будет выбирать, насколько изменить значение.
 */
public class DocxParagraphLevelComparator implements Comparator<XWPFParagraph> {

    @Override
    public int compare(XWPFParagraph paragraph1, XWPFParagraph paragraph2) {
        DotNumbersInHeader dotNumbersInHeader1 = new DotNumbersInHeader(paragraph1);
        DotNumbersInHeader dotNumbersInHeader2 = new DotNumbersInHeader(paragraph2);
        return dotNumbersInHeader1.levelDepth() - dotNumbersInHeader2.levelDepth();
    }
}
