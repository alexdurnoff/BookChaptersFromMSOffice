package ru.durnov.doc;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.poi.hwpf.usermodel.Picture;
import ru.durnov.chapters.Image;

import java.io.IOException;

public class DocImage implements Image {
    private final Picture picture;
    private String name;

    public DocImage(Picture picture) {
        this.picture = picture;
        this.name = this.picture.suggestFullFileName();
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        System.out.println("before name is " + this.name);
        this.name = name;
        System.out.println("new name is " + this.name);
    }

    @Override
    public byte[] asByteArray() throws IOException, TranscoderException {
        return this.picture.getContent();
    }
}
