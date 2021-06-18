package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParagraphList {
    private final List<Paragraph> paragraphList = new ArrayList<>();

    public ParagraphList(List<Section> sectionList) {
        sectionList.forEach(section -> {
            int count = section.numParagraphs();
            for (int i = 0; i < count; i++) this.paragraphList.add(section.getParagraph(i));
        });
    }

    public ParagraphList(HWPFDocument hwpfDocument){
        Range range = hwpfDocument.getRange();
        int numParagraphs = range.numParagraphs();
        for (int i = 0; i < numParagraphs; i++) this.paragraphList.add(range.getParagraph(i));
    }

    public List<Paragraph> list() {
        return Collections.unmodifiableList(this.paragraphList);
    }
}
