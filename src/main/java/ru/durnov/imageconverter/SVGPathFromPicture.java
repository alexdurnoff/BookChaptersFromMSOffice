package ru.durnov.imageconverter;

import org.apache.poi.xwpf.usermodel.XWPFPictureData;

import java.nio.file.Path;

public class SVGPathFromPicture {
    private final XWPFPictureData xwpfPictureData;

    public SVGPathFromPicture(XWPFPictureData xwpfPictureData) {
        this.xwpfPictureData = xwpfPictureData;
    }

    public Path path() {
        if (xwpfPictureData.getFileName().endsWith(".emf")) return Path.of(
                xwpfPictureData.getFileName().replace(".emf", ".svg")
        );
        return Path.of(xwpfPictureData.getFileName() + ".svg");
    }
}
