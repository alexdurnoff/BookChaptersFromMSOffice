package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Section;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Deprecated
public class ParagraphsWithSections {
    private final List<ParagraphWithSection> paragraphWithSectionList;

    public ParagraphsWithSections(List<ParagraphWithSection> paragraphWithSectionList) {
        this.paragraphWithSectionList = paragraphWithSectionList;
    }

    public ParagraphsWithSections(HWPFDocument hwpfDocument){
        List<Section> sectionList = new SectionList(hwpfDocument).list();
        this.paragraphWithSectionList = new ArrayList<>();
        sectionList.forEach(section -> {
            int count = section.numParagraphs();
            for (int i = 0; i < count; i++){
                paragraphWithSectionList.add(
                        new ParagraphWithSection(
                                section.getParagraph(i),
                                section
                        )
                );
            }
        });
    }

    public List<ParagraphWithSection> list(){
        return Collections.unmodifiableList(this.paragraphWithSectionList);
    }
}
