package ru.durnov.docx;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import ru.durnov.chapters.Image;
import ru.durnov.imageconverter.Emf2SVGConverter;
import ru.durnov.imageconverter.SVGToPNGConverter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DocxEmfImage implements Image {
    private final XWPFPictureData xwpfPictureData;

    public DocxEmfImage(XWPFPictureData xwpfPictureData) {
        this.xwpfPictureData = xwpfPictureData;
    }

    @Override
    public String name() {
        return this.xwpfPictureData.getFileName();
    }

    @Override
    public byte[] asByteArray() throws IOException, TranscoderException {
        return new SVGToPNGConverter(
                new Emf2SVGConverter(this.xwpfPictureData).asByteArray()
        ).asByteArray();
    }
}
