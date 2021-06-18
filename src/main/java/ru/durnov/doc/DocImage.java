package ru.durnov.doc;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.poi.hwpf.usermodel.Picture;
import ru.durnov.chapters.Image;

import java.io.IOException;

public class DocImage implements Image {
    private final Picture picture;

    public DocImage(Picture picture) {
        this.picture = picture;
    }

    @Override
    public String name() {
        return this.picture.getDescription();
    }

    @Override
    public byte[] asByteArray() throws IOException, TranscoderException {
        return this.picture.getContent();
    }
}
