package ru.durnov.docx;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import ru.durnov.chapters.Image;
import ru.durnov.imageconverter.Emf2SVGConverter;
import ru.durnov.imageconverter.SVGToPNGConverter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class EmfImage implements Image {
    private final String name;
    private final byte[] bytes;

    public EmfImage(XWPFPictureData xwpfPictureData) {
        this.name = xwpfPictureData.getFileName();
        this.bytes = xwpfPictureData.getData();
    }

    public EmfImage(Picture picture) {
        this.name = picture.getDescription() + "." + picture.suggestFileExtension();
        this.bytes = picture.getContent();
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public byte[] asByteArray() throws IOException, TranscoderException {
        return new SVGToPNGConverter(
                new Emf2SVGConverter(this.bytes).asByteArray()
        ).asByteArray();
    }
}
