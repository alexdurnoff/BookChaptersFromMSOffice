package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import ru.durnov.chapters.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DocxWMFImage implements Image {
    private final XWPFPictureData xwpfPictureData;

    public DocxWMFImage(XWPFPictureData xwpfPictureData) {
        this.xwpfPictureData = xwpfPictureData;
    }

    @Override
    public String name() {
        return this.xwpfPictureData.getFileName().replace(".wmf", ".png");
    }

    @Override
    public byte[] asByteArray() throws IOException {
        Files.newOutputStream(Path.of(xwpfPictureData.getFileName())).write(xwpfPictureData.getData());
        String[] args = new String[1];
        args[0] = xwpfPictureData.getFileName();
        org.apache.batik.apps.rasterizer.Main.main(args);
        byte[] bytes = Files.newInputStream(Path.of(name())).readAllBytes();
        Files.delete(Path.of(xwpfPictureData.getFileName()));
        Files.delete(Path.of(name()));
        return bytes;
    }
}
