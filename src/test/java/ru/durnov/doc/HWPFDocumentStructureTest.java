package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.*;
import org.junit.jupiter.api.Test;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class HWPFDocumentStructureTest {

    @Test
    void test1() throws IOException, ParserConfigurationException, TransformerException {
        HWPFDocument hwpfDocument = new HWPFDocument(
                Files.newInputStream(Path.of("Test/prikaz1 with pictures.doc"))
        );
        Range range = hwpfDocument.getRange();
        int numCharacterRuns = range.numCharacterRuns();
        int numParagraphs = range.numParagraphs();
        PicturesTable picturesTable = hwpfDocument.getPicturesTable();
        for (int i = 0; i < numCharacterRuns; i++){
            CharacterRun characterRun = range.getCharacterRun(i);
            if (picturesTable.hasEscherPicture(characterRun)){
                Picture picture = picturesTable.getAllPictures().get(0);
                characterRun.delete();
                characterRun = range.getCharacterRun(i+1);
                characterRun.insertBefore("img=" + picture.suggestFullFileName());
            }
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        hwpfDocument.write(byteArrayOutputStream);
        hwpfDocument.write(new File("/tmp/aaa.doc"));
        byte[] bytes = byteArrayOutputStream.toByteArray();
        hwpfDocument.close();
        HWPFDocument hwpfDocument1 = new HWPFDocument(new ByteArrayInputStream(bytes));
        range = hwpfDocument1.getRange();
        numCharacterRuns = range.numCharacterRuns();
        for (int i = 0; i < numCharacterRuns; i++){
            CharacterRun characterRun = range.getCharacterRun(i);
            System.out.println(characterRun.text());
        }
    }


}
