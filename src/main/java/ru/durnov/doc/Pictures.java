package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Picture;
import ru.durnov.chapters.Chapter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Pictures {
    private final Queue<Picture> pictureQueue;
    private final PicturesTable picturesTable;

    public Pictures(HWPFDocument hwpfDocument){
        this.picturesTable = hwpfDocument.getPicturesTable();
        this.pictureQueue = new ArrayDeque<>(this.picturesTable.getAllPictures());
    }

    public  boolean paragraphHasPicture(CharacterRun characterRun){
        return this.picturesTable.hasEscherPicture(characterRun) || this.picturesTable.hasPicture(characterRun);
    }

    public Picture nextPicture(){
        Picture picture = this.pictureQueue.peek();
        this.pictureQueue.remove();
        return picture;
    }

}
