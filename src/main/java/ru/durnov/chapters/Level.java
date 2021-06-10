package ru.durnov.chapters;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import ru.durnov.docx.DocxParagraphLevelComparator;
import ru.durnov.docx.DocxStyleMap;

import java.util.Comparator;

/**
 * Class encapsulate current level. Change level by new paragraph and previous paragraph.
 */
public class Level {
    private int value;
    private XWPFParagraph lastHeaderParagraph;
    private final DocxStyleMap docxStyleMap;
    private final Comparator<XWPFParagraph> levelComparator = new DocxParagraphLevelComparator();

    public Level(XWPFDocument xwpfDocument){
        this.docxStyleMap = new DocxStyleMap(xwpfDocument);
    }

    public Level(DocxStyleMap docxStyleMap){
        this.docxStyleMap = docxStyleMap;
    }

    public int levelByParagraph(XWPFParagraph newParagraph){
        if (lastHeaderParagraph == null) lastHeaderParagraph = newParagraph;
        if (this.docxStyleMap.paragraphIsHeader(newParagraph)){
            lastHeaderParagraph = newParagraph;
            value = this.docxStyleMap.levelByParagraph(newParagraph);
            return value;
        }
        if (this.docxStyleMap.paragraphIsHeader(lastHeaderParagraph)){
            lastHeaderParagraph = newParagraph;
            return ++value;
        }
        int compareResult = levelComparator.compare(lastHeaderParagraph, newParagraph);
        value -= compareResult;
        lastHeaderParagraph = newParagraph;
        return value;
    }

    public boolean paragraphIsHeader(IBodyElement iBodyElement){
        return this.docxStyleMap.paragraphIsHeader(iBodyElement);
    }

}
