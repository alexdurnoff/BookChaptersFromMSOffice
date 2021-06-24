package ru.durnov.doc;

import org.apache.batik.transcoder.TranscoderException;
import org.junit.jupiter.api.Test;
import ru.durnov.chapters.Image;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DocImageExtensionTest {

    @Test
    void testExtension(){
        Image image = new Image() {
            @Override
            public String name() {
                return "0.jpg";
            }

            @Override
            public byte[] asByteArray() throws IOException, TranscoderException {
                return new byte[0];
            }
        };
        String extension = new DocImageExtension(image).extension();
        assertEquals(extension, ".jpg");
    }

    @Test
    void testImageWithNameWithMultiDots(){
        Image image = new Image() {
            @Override
            public String name() {
                return "image1.3.jpg";
            }

            @Override
            public byte[] asByteArray() throws IOException, TranscoderException {
                return new byte[0];
            }

        };
        String extension = new DocImageExtension(image).extension();
        assertEquals(extension, ".jpg");
    }

}