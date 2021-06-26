package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.Paragraph;
import ru.durnov.chapters.Index;

@Deprecated
public class DocElementFactory {
    private final ParagraphWithSection paragraphWithSection;
    private final Paragraph paragraph;
    private final Index index;
    private final PicturesTable picturesTable;
    private final HWPFDocument hwpfDocument;

    public DocElementFactory(ParagraphWithSection paragraphWithSection,
                             Index index,
                             PicturesTable picturesTable,
                             HWPFDocument hwpfDocument) {
        this.paragraphWithSection = paragraphWithSection;
        this.paragraph = paragraphWithSection.paragraph();
        this.index = index;
        this.picturesTable = picturesTable;
        this.hwpfDocument = hwpfDocument;
    }


    public DocContentElement docContentElement() {
        if (paragraph.isInTable()){

        }
        if (paragraph.isInList()) {
            return new DocListContentELement(
                    paragraphWithSection,
                    index
            );
        }
        return null;
    }
}
