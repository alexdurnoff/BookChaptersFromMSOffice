package ru.durnov.docx;

import org.apache.batik.transcoder.TranscoderException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class DocxWMFImageTest {

    @Test
    void testConvertImage() throws IOException, TranscoderException {
        Path tmpPath = Path.of("image3.wmf");
        Path outputPath = Path.of("Test/image3.png");
        if (Files.notExists(tmpPath)) Files.copy(Path.of("Test/image3.wmf"), tmpPath);
        DocxWMFImage docxWMFImage = new DocxWMFImage("image3.wmf");
        byte[] bytes = docxWMFImage.asByteArray();
        Files.write(outputPath, bytes);
        byte[] outputFileByteArray = Files.readAllBytes(outputPath);
        assertEquals(outputFileByteArray.length, 93370);
        Files.delete(tmpPath);
    }

}