package ru.durnov.oldword;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;

import java.util.List;

public class ParagraphCoordinates implements Coordinates{
    private final int from;
    private final Paragraph paragraph;
    private final StringBuilder stringBuilder;

    public ParagraphCoordinates(HWPFDocument hwpfDocument, Paragraph paragraph, int from) {
        this.from = from;
        this.stringBuilder = hwpfDocument.getText();
        this.paragraph = paragraph;
    }

    public ParagraphCoordinates(HWPFDocument hwpfDocument,
                                List<Paragraph> paragraphList,
                                Paragraph paragraph) {
        this.paragraph = paragraph;
        this.stringBuilder = hwpfDocument.getText();
        int numParagraphs = paragraphList.size();
        int start = 0;
        for (int i = 0; i < numParagraphs; i++){
            Paragraph paragraph1 = paragraphList.get(i);
            if (paragraph1.equals(paragraph)) break;
            start += paragraph1.text().length();
        }
        this.from = start;
    }


    @Override
    public int start() {
        return this.stringBuilder.indexOf(paragraph.text(), from);
    }

    @Override
    public int stop() {
        return this.start() + paragraph.text().length();
    }
}
