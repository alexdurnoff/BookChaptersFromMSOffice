package ru.durnov.doc;

import org.apache.poi.hwpf.usermodel.Paragraph;
import ru.durnov.docx.DotNumbersInHeader;

import java.util.Comparator;

public class DocParagraphLevelComparator implements Comparator<Paragraph> {
    @Override
    public int compare(Paragraph paragraph1, Paragraph paragraph2) {
        DotNumbersInHeader dotNumbersInHeader1 = new DotNumbersInHeader(paragraph1);
        DotNumbersInHeader dotNumbersInHeader2 = new DotNumbersInHeader(paragraph2);
        int result = dotNumbersInHeader1.levelDepth() - dotNumbersInHeader2.levelDepth();
        System.out.println(paragraph1.getCharacterRun(0).text()
                + "-" + paragraph2.getCharacterRun(0).text()
                +"=" + result);
        return result;
    }
}
