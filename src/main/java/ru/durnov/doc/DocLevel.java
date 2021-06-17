package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;

import java.util.Comparator;

public class DocLevel {
    private int value;
    private Paragraph lastHeaderParagraph;
    private final DocStyleMap docStyleMap;
    private final Comparator<Paragraph> levelComparator = new DocParagraphLevelComparator();

    public DocLevel(DocStyleMap docStyleMap){
        this.docStyleMap = docStyleMap;
    }

    public DocLevel(HWPFDocument hwpfDocument){
        this.docStyleMap = new DocStyleMap(hwpfDocument);
    }

    public int levelByParagraph(Paragraph newParagraph){
        if (lastHeaderParagraph == null) lastHeaderParagraph = newParagraph;
        if (docStyleMap.paragraphIsHeader(newParagraph)){
            lastHeaderParagraph = newParagraph;
            value = this.docStyleMap.levelByParagraph(newParagraph);
            return value;
        }
        if (this.docStyleMap.paragraphIsHeader(lastHeaderParagraph)){
            lastHeaderParagraph = newParagraph;
            return ++value;
        }
        int compareResult = levelComparator.compare(lastHeaderParagraph, newParagraph);
        value -= compareResult;
        lastHeaderParagraph = newParagraph;
        return value;
    }
}
