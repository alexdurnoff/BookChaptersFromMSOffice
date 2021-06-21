package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;

import java.util.Comparator;

public class DocLevel {
    private int value;
    private boolean lastParagraphWasHeader;
    private Paragraph lastParagraph;
    private final DocStyleMap docStyleMap;
    private final Comparator<Paragraph> levelComparator = new DocParagraphLevelComparator();

    public DocLevel(DocStyleMap docStyleMap){
        this.docStyleMap = docStyleMap;
    }

    public int levelByParagraph(Paragraph newParagraph){
        if (lastParagraph == null) lastParagraph = newParagraph;
        if (docStyleMap.paragraphIsHeader(newParagraph)){
            lastParagraph = newParagraph;
            lastParagraphWasHeader = true;
            value = this.docStyleMap.levelByParagraph(newParagraph);
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
}
