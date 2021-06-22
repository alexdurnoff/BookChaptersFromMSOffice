package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.Range;

public class HWPFDocumentWithOutPictures {
    private final HWPFDocument hwpfDocument;

    public HWPFDocumentWithOutPictures(HWPFDocument hwpfDocument) {
        this.hwpfDocument = hwpfDocument;
    }

    public HWPFDocument hwpfDocument(){
        PicturesTable picturesTable = hwpfDocument.getPicturesTable();
        Range range = hwpfDocument.getRange();
        int numCharacterRuns = range.numCharacterRuns();
        for (int i = 0; i < numCharacterRuns; i++){
            CharacterRun characterRun = range.getCharacterRun(i);
            if (picturesTable.hasPicture(characterRun)) {
                Picture picture = picturesTable.extractPicture(characterRun, true);

            }
        }
        return null;
    }
}
