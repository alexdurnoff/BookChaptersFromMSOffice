package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.util.Comparator;

/**
 * Class encapsulate current level. Change level by new paragraph and previous paragraph.
 */
public class DocxLevel {
    private int value;
    private boolean lastParagraphWasHeader;
    private XWPFParagraph lastParagraph;
    private final DocxStyleMap docxStyleMap;
    private final Comparator<XWPFParagraph> levelComparator = new DocxParagraphLevelComparator();

    public DocxLevel(XWPFDocument xwpfDocument){
        this.docxStyleMap = new DocxStyleMap(xwpfDocument);
    }

    public DocxLevel(DocxStyleMap docxStyleMap){
        this.docxStyleMap = docxStyleMap;
    }



    public int levelByParagraph(XWPFParagraph newParagraph){
        if (lastParagraph == null) lastParagraph = newParagraph;
        if (this.docxStyleMap.paragraphIsHeader(newParagraph)){
            lastParagraph = newParagraph;
            lastParagraphWasHeader = true;
            value = this.docxStyleMap.levelByParagraph(newParagraph);
            return value;
        }
        if (lastParagraphWasHeader){
            lastParagraph = newParagraph;
            lastParagraphWasHeader = false;
            return ++value;
        }
        lastParagraphWasHeader = false;
        int compareResult = levelComparator.compare(lastParagraph, newParagraph);
        value -= compareResult;
        lastParagraph = newParagraph;
        return value;
    }

    public boolean paragraphIsHeader(IBodyElement iBodyElement){
        return this.docxStyleMap.paragraphIsHeader(iBodyElement);
    }

}
