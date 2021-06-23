package ru.durnov.oldword;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.Range;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayDeque;
import java.util.Queue;

public class HWPFDocumentWithoutPictures {
    private final HWPFDocument hwpfDocument;
    private final PicturesTable picturesTable;
    private final Queue<Picture> pictureQueue;

    public HWPFDocumentWithoutPictures(HWPFDocument hwpfDocument) {
        this.hwpfDocument = hwpfDocument;
        this.picturesTable = this.hwpfDocument.getPicturesTable();
        this.pictureQueue = new ArrayDeque<>(this.hwpfDocument.getPicturesTable().getAllPictures());
    }

    public HWPFDocument document() throws IOException {
        Range range = this.hwpfDocument.getRange();
        int numCharacterRuns = range.numCharacterRuns();
        for (int i = 0; i < numCharacterRuns; i++){
            CharacterRun characterRun = range.getCharacterRun(i);
            if (picturesTable.hasEscherPicture(characterRun) ||
            picturesTable.hasPicture(characterRun)){
                System.out.println("detected run with pictures");
                Picture picture = this.pictureQueue.remove();
                //characterRun.delete();
                //characterRun = range.getCharacterRun(i+1);
                characterRun.insertBefore("Картинка=" + picture.suggestFullFileName());
                if (this.pictureQueue.isEmpty()) break;
            }
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.hwpfDocument.write(byteArrayOutputStream);
        this.hwpfDocument.close();
        byte[] bytes = byteArrayOutputStream.toByteArray();
        System.out.println(bytes.length);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        return new HWPFDocument(byteArrayInputStream);
    }
}
