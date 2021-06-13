package ru.durnov.imageconverter;

import org.apache.batik.transcoder.TranscoderException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class SVGToPNGConverterTest {

    @Test
    void testPngConverter() throws IOException, TranscoderException {
        Path outputPath = Path.of("Test/image4.png");
        Path inputPath = Path.of("Test/image4.svg");
        Files.deleteIfExists(outputPath);
        SVGToPNGConverter converter = new SVGToPNGConverter(inputPath);
        byte[] bytes = converter.asByteArray();
        Files.newOutputStream(outputPath).write(bytes);
        assertEquals(Files.readAllBytes(outputPath).length, 94298);
    }

}