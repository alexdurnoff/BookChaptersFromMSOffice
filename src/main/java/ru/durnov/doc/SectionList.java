package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;

import java.util.ArrayList;
import java.util.List;

public class SectionList {
    private final List<Section> sectionList = new ArrayList<>();

    public SectionList(HWPFDocument hwpfDocument) {
        Range range = hwpfDocument.getRange();
        int count = range.numSections();
        for (int i = 0; i < count; i++) this.sectionList.add(range.getSection(i));

    }

    public List<Section> list() {
        return this.sectionList;
    }
}
