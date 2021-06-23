package ru.durnov.queue;

import com.lowagie.text.pdf.PRAcroForm;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.hwpf.usermodel.Table;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Ranges{
    private final Queue<Range> rangeQueue = new ArrayDeque<>();
    private final Map<Range, Section> rangeSectionMap = new HashMap<>();


    public Ranges(HWPFDocument hwpfDocument) {
        Range range = hwpfDocument.getRange();
        int numSections = range.numSections();
        for (int j = 0; j < numSections; j++) {
            Section section = range.getSection(j);
            int numParagraphs = section.numParagraphs();
            for (int i = 0; i < numParagraphs; i++) {
                Paragraph paragraph = range.getParagraph(i);
                if (paragraph.isInTable()) {
                    Table table = range.getTable(paragraph);
                    this.rangeSectionMap.put(table, section);
                    this.rangeQueue.add(table);
                    while (paragraph.isInTable()) {
                        i++;
                        if (i == numParagraphs) break;
                        paragraph = range.getParagraph(i);
                    }
                } else {
                    this.rangeQueue.add(paragraph);
                    this.rangeSectionMap.put(paragraph, section);
                }
            }
        }
    }



    public boolean hasNext(){
        return ! this.rangeQueue.isEmpty();
    }

    public Range nextRange(){
        return this.rangeQueue.peek();
    }

    public void removeRange(){
        this.rangeQueue.remove();
    }

    public Section sectionByRange(Range range) {
        return this.rangeSectionMap.get(range);
    }

    public void removeTableParagraphs() {
        Range range = this.nextRange();
        if (range instanceof Paragraph){
            Paragraph paragraph = (Paragraph) range;
            while (paragraph.isInTable()){
                this.removeRange();
                range = this.nextRange();
                if (range instanceof Paragraph){
                    paragraph = (Paragraph) range;
                } else {
                    break;
                }
            }
        }

    }
}
